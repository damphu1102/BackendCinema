package zalopay.service.impl;


import org.json.JSONObject;
import org.springframework.stereotype.Service;
import zalopay.config.ZaloPayConfig;
import zalopay.dto.OrderRequest;
import zalopay.dto.OrderResponse;
import zalopay.model.Order;
import zalopay.repository.ZaloPayRepository;
import zalopay.service.ZaloPayService;
import zalopay.util.HMACUtil;

import java.util.Map;
import java.util.Random;

@Service
public class ZaloPayServiceImpl implements ZaloPayService {

    private final ZaloPayRepository zaloPayRepository;

    public ZaloPayServiceImpl(ZaloPayRepository zaloPayRepository) {
        this.zaloPayRepository = zaloPayRepository;
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) throws Exception {
        Order order = buildOrder(request);
        JSONObject result = zaloPayRepository.createOrder(order.toMap());
        return new OrderResponse(result);
    }

    private Order buildOrder(OrderRequest request) {
        Random rand = new Random();
        int randomId = rand.nextInt(1000000);
        String appTransId = ZaloPayConfig.getCurrentTimeString("yyMMdd") + "_" + randomId;

        String itemsJson = new JSONObject(request.getItems()).toString();
        String embedDataJson = new JSONObject(request.getEmbedData()).toString();

        String macInput = String.format("%s|%s|%s|%s|%s|%s|%s",
                ZaloPayConfig.getConfig("app_id"),
                appTransId,
                request.getAppUser(),
                request.getAmount(),
                System.currentTimeMillis(),
                embedDataJson,
                itemsJson);

        String mac = "";
        try {
            mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256,
                    ZaloPayConfig.getConfig("key1"), macInput);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate MAC", e);
        }

        return new Order(
                ZaloPayConfig.getConfig("app_id"),
                appTransId,
                System.currentTimeMillis(),
                request.getAppUser(),
                request.getAmount(),
                request.getDescription(),
                request.getBankCode(),
                itemsJson,
                embedDataJson,
                mac
        );
    }
}
