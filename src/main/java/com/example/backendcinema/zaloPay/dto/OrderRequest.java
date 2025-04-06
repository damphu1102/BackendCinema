package zalopay.dto;

import lombok.Data;

import java.util.Map;

@Data
public class OrderRequest {
    private String appUser;
    private long amount;
    private String description;
    private String bankCode;
    private Map<String, Object> embedData;
    private Map<String, Object>[] items;
}
