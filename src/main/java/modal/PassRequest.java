package modal;

import lombok.Data;

@Data
public class PassRequest {
    private int accountId;
    private String passWord;
}
