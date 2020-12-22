package id.aguswmika.pembayarankontraklapak.model.result;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private String data;
    @SerializedName("error")
    private Boolean error;

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }

    public Boolean getError() {
        return error;
    }
}
