package zalopay.dto;

import lombok.Data;
import org.json.JSONObject;

@Data
public class OrderResponse {
    private int returnCode;
    private String returnMessage;
    private String orderUrl;
    private String orderToken;
    private String zpTransToken;
    private String appTransId;

    // Constructors
    public OrderResponse(JSONObject json) {
        this.returnCode = json.getInt("returncode");
        this.returnMessage = json.getString("returnmessage");
        this.orderUrl = json.optString("orderurl", "");
        this.orderToken = json.optString("ordertoken", "");
        this.zpTransToken = json.optString("zptranstoken", "");
        this.appTransId = json.optString("apptransid", "");
    }
}
