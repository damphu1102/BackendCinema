package zalopay.model;

import java.util.Map;

public class Order {
    private String appId;
    private String appTransId;
    private long appTime;
    private String appUser;
    private long amount;
    private String description;
    private String bankCode;
    private String item;
    private String embedData;
    private String mac;

    // Constructors, getters, and setters
    public Order() {}

    public Order(String appId, String appTransId, long appTime, String appUser,
                 long amount, String description, String bankCode,
                 String item, String embedData, String mac) {
        this.appId = appId;
        this.appTransId = appTransId;
        this.appTime = appTime;
        this.appUser = appUser;
        this.amount = amount;
        this.description = description;
        this.bankCode = bankCode;
        this.item = item;
        this.embedData = embedData;
        this.mac = mac;
    }

    // Getters and setters
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    // ... (other getters and setters similar to above)

    public Map<String, String> toMap() {
        return Map.of(
                "app_id", appId,
                "app_trans_id", appTransId,
                "app_time", String.valueOf(appTime),
                "app_user", appUser,
                "amount", String.valueOf(amount),
                "description", description,
                "bank_code", bankCode,
                "item", item,
                "embed_data", embedData,
                "mac", mac
        );
    }
}
