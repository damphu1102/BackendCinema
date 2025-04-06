package zalopay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zalopay.dto.OrderRequest;
import zalopay.dto.OrderResponse;
import zalopay.service.ZaloPayService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final ZaloPayService zaloPayService;

    @Autowired
    public OrderController(ZaloPayService zaloPayService) {
        this.zaloPayService = zaloPayService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        try {
            OrderResponse response = zaloPayService.createOrder(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new OrderResponse(new org.json.JSONObject()
                            .put("returncode", -1)
                            .put("returnmessage", e.getMessage())));
        }
    }
}
