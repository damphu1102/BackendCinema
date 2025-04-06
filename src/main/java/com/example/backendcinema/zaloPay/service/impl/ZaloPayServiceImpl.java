package com.example.backendcinema.zaloPay.service.impl;

import com.example.backendcinema.zaloPay.config.ZaloPayConfig;
import com.example.backendcinema.zaloPay.dto.OrderRequest;
import com.example.backendcinema.zaloPay.dto.OrderResponse;
import com.example.backendcinema.zaloPay.model.Order;
import com.example.backendcinema.zaloPay.repository.ZaloPayRepository;
import com.example.backendcinema.zaloPay.service.ZaloPayService;
import com.example.backendcinema.zaloPay.util.HMACUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ZaloPayServiceImpl implements ZaloPayService {

    @Autowired
    private ZaloPayRepository zaloPayRepository;

    @Override
    public OrderResponse createZaloPayOrder(OrderRequest orderRequest) {
        try {
            // 1. Tạo yêu cầu thanh toán ZaloPay
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("app_id", ZaloPayConfig.APP_ID);
            requestData.put("app_trans_id", generateAppTransId());
            requestData.put("app_user", "user123"); // Thay bằng thông tin người dùng thực tế
            requestData.put("app_time", System.currentTimeMillis());
            requestData.put("amount", orderRequest.getAmount());
            requestData.put("description", orderRequest.getDescription());
            requestData.put("bank_code", "zalopayapp");

            String data = ZaloPayConfig.APP_ID + "|" + requestData.get("app_trans_id") + "|" +
                    requestData.get("app_user") + "|" + requestData.get("amount") + "|" +
                    requestData.get("app_time") + "|" + requestData.get("description");
            String mac = HMACUtil.hmacSHA256(data, ZaloPayConfig.KEY1);
            requestData.put("mac", mac);

            // 2. Gửi yêu cầu đến ZaloPay API
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(ZaloPayConfig.API_URL);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(new JSONObject(requestData).toString()));

            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            httpClient.close();

            // 3. Xử lý phản hồi từ ZaloPay
            try {
                JSONObject jsonResponse = new JSONObject(responseBody);

                if (jsonResponse.getInt("return_code") == 1) {
                    // Thanh toán thành công
                    Order order = new Order();
                    order.setAmount(orderRequest.getAmount());
                    order.setDescription(orderRequest.getDescription());
                    order.setZaloPayOrderId(jsonResponse.getString("zp_trans_id"));
                    zaloPayRepository.save(order);

                    OrderResponse orderResponse = new OrderResponse();
                    orderResponse.setOrderUrl(jsonResponse.getString("order_url"));
                    orderResponse.setReturnCode("1");
                    orderResponse.setReturnMessage("Tạo đơn hàng thành công");
                    return orderResponse;
                } else {
                    // Thanh toán thất bại
                    OrderResponse orderResponse = new OrderResponse();
                    orderResponse.setReturnCode(String.valueOf(jsonResponse.getInt("return_code")));
                    orderResponse.setReturnMessage(jsonResponse.getString("return_message"));
                    return orderResponse;
                }
            } catch (org.json.JSONException e) {
                // Xử lý trường hợp phản hồi không phải là JSON hợp lệ
                System.err.println("Invalid JSON response from ZaloPay: " + responseBody);
                e.printStackTrace();
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setReturnCode("-1");
                orderResponse.setReturnMessage("Lỗi xử lý JSON phản hồi");
                return orderResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setReturnCode("-1");
            orderResponse.setReturnMessage("Lỗi hệ thống");
            return orderResponse;
        }
    }

    private String generateAppTransId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String currentDate = dateFormat.format(new Date());
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return currentDate + "_" + randomNumber;
    }
}
