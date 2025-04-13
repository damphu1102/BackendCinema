package com.example.backendcinema.Payment.Service;

import com.example.backendcinema.Payment.Config.ZalopayConfig;
import com.example.backendcinema.Payment.cryto.HMACUtil;
import com.example.backendcinema.Payment.modal.OrderSeat;
import com.example.backendcinema.Payment.modal.ZalopayTransaction;
import com.example.backendcinema.Payment.repository.OrderSeatRepository;
import com.example.backendcinema.Payment.repository.TransactionRepository;
import com.example.backendcinema.Payment.request.ZalopayRequest;
import com.example.backendcinema.entity.Seat.SeatStatus;
import com.example.backendcinema.repository.SeatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ZalopayService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private OrderSeatRepository orderSeatRepository;

    private static final Logger logger = LoggerFactory.getLogger(ZalopayService.class);


    private static String getCurrentTimeString() {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat("yyMMdd");
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    public String createOrder(ZalopayRequest zaloPayRequest) {
        Random rand = new Random();
        int randomId = rand.nextInt(1000000);
        String appTransId = getCurrentTimeString() + "_" + randomId;


        if (zaloPayRequest == null || zaloPayRequest.getAmount() <= 0) {
            return "{\"error\": \"Amount is required and must be greater than 0\"}";
        }

        int amount = zaloPayRequest.getAmount();
        List<Integer> selectedSeats = zaloPayRequest.getSelectedSeats();

        // Lưu thông tin ghế đã chọn vào bảng tạm
        for (Integer seatId : selectedSeats) {
            OrderSeat orderSeat = new OrderSeat(appTransId, seatId);
            orderSeatRepository.save(orderSeat);
        }

        Map<String, Object> order = new LinkedHashMap<>();
        order.put("app_id", ZalopayConfig.config.get("app_id"));
        order.put("app_trans_id", getCurrentTimeString() + "_" + randomId);
        order.put("app_time", System.currentTimeMillis());
        order.put("app_user", "user123");
        order.put("amount", amount);
        order.put("description", "HP Cinema - Payment #" + randomId);
        order.put("bank_code", "");
        order.put("item", "[{}]");
        JSONObject embedDataJson = new JSONObject();
        embedDataJson.put("redirecturl", "http://localhost:3000/");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            embedDataJson.put("selected_seats", objectMapper.writeValueAsString(selectedSeats));
            logger.info("Embed Data JSON sent to Zalopay: {}", embedDataJson); // Thêm dòng log này
        } catch (Exception e) {
            logger.error("Error converting selected seats to JSON", e);
        }
        order.put("embed_data", embedDataJson.toString());

        order.put("callback_url",
                "http://localhost:3000/api/zalopay/callback");

        String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" + order.get("app_user") + "|"
                + order.get("amount") + "|" + order.get("app_time") + "|" + order.get("embed_data") + "|"
                + order.get("item");

        String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, ZalopayConfig.config.get("key1"), data);
        order.put("mac", mac);

//        System.out.println("Generated MAC: " + mac);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(ZalopayConfig.config.get("endpoint"));

            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, Object> entry : order.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            post.setEntity(new UrlEncodedFormEntity(params));

            try (CloseableHttpResponse response = client.execute(post)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder resultJsonStr = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    resultJsonStr.append(line);
                }

//                System.out.println("Zalopay Response: " + resultJsonStr.toString());

                return resultJsonStr.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to create order: " + e.getMessage() + "\"}";
        }
    }

    public String getOrderStatus(String appTransId) {
        String data = ZalopayConfig.config.get("app_id") + "|" + appTransId + "|" + ZalopayConfig.config.get("key1");
        String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, ZalopayConfig.config.get("key1"), data);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(ZalopayConfig.config.get("orderstatus"));

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("app_id", ZalopayConfig.config.get("app_id")));
            params.add(new BasicNameValuePair("app_trans_id", appTransId));
            params.add(new BasicNameValuePair("mac", mac));

            post.setEntity(new UrlEncodedFormEntity(params));

            try (CloseableHttpResponse response = client.execute(post)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder resultJsonStr = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    resultJsonStr.append(line);
                }

                return resultJsonStr.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to get order status: " + e.getMessage() + "\"}";
        }
    }

    public String processCallback(@RequestParam Map<String, String> queryParams) {
        logger.info("Received Zalopay callback with parameters: {}", queryParams);

        String amount = queryParams.get("amount");
        String appid = queryParams.get("appid");
        String apptransid = queryParams.get("apptransid");
        String bankcode = queryParams.get("bankcode");
        String checksumReceived = queryParams.get("checksum");
        String discountamount = queryParams.get("discountamount");
        String pmcid = queryParams.get("pmcid");
        String status = queryParams.get("status");


        // Tạo chuỗi dữ liệu để kiểm tra checksum
        String data = appid + "|" + apptransid + "|" + pmcid + "|" + bankcode + "|" + amount + "|" + discountamount + "|" + status;
        String generatedChecksum = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, ZalopayConfig.config.get("key2"), data);

        logger.info("Received Checksum: {}", checksumReceived);
        logger.info("Generated Checksum: {}", generatedChecksum);

        assert generatedChecksum != null;
        if (generatedChecksum.equals(checksumReceived)) {
            logger.info("Checksum validation successful for apptransid: {}", apptransid);

            try {
                String orderStatusResponse = getOrderStatus(apptransid);
                JSONObject orderStatusJson = new JSONObject(orderStatusResponse);
                String returnMessage = orderStatusJson.optString("return_message");
                int returnCode = orderStatusJson.optInt("return_code");
                long serverTime = orderStatusJson.optLong("server_time");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+7")); // Đảm bảo đúng múi giờ Việt Nam
                String formattedServerTime = sdf.format(new Date(serverTime));

                ZalopayTransaction transaction = new ZalopayTransaction();
                transaction.setAppTransId(apptransid);
                transaction.setAmount(Integer.parseInt(amount));
                transaction.setStatus(Integer.parseInt(status));
                transaction.setMessage(returnMessage);
                transaction.setTimestamp(formattedServerTime);
                transactionRepository.save(transaction);
                logger.info("Transaction saved successfully for apptransid: {}", apptransid);

                // Cập nhật trạng thái ghế nếu giao dịch thành công
                if (status.equals("1") && returnCode == 1) {
                    List<OrderSeat> orderSeats = orderSeatRepository.findByAppTransId(apptransid);
                    for (OrderSeat orderSeat : orderSeats) {
                        seatRepository.findById(orderSeat.getSeatId()).ifPresent(seat -> {
                            seat.setSeatStatus(SeatStatus.success);
                            seatRepository.save(seat);
                            logger.info("Updated status to success for seat ID: {} (apptransid: {})", seat.getSeat_id(), apptransid);
                        });
                    }
                    // Tùy chọn: Xóa thông tin ghế tạm sau khi cập nhật thành công
                    orderSeatRepository.deleteAll(orderSeats);
                    logger.info("Deleted temporary order seat information for apptransid: {}", apptransid);
                }

                return "{\"return_code\": 1, \"return_message\": \"success\"}";
            } catch (Exception e) {
                logger.error("Error saving transaction for apptransid: {}", apptransid, e);
                return "{\"return_code\": 0, \"return_message\": \"Failed to save transaction\"}";
            }

        } else {
            logger.warn("Checksum validation failed for apptransid: {}", apptransid);
            return "{\"return_code\": 0, \"return_message\": \"checksum fail\"}";
        }
    }
}
