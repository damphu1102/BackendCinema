package zalopay.service;

import zalopay.dto.OrderRequest;
import zalopay.dto.OrderResponse;

public interface ZaloPayService {
    OrderResponse createOrder(OrderRequest request) throws Exception;

}
