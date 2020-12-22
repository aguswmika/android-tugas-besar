package id.aguswmika.pembayarankontraklapak.model.result;

import com.google.gson.annotations.SerializedName;

public class PembayaranKontrakResultTemp {
    @SerializedName("data")
    private String data;
    @SerializedName("error")
    private Boolean error;
    @SerializedName("message")
    private String message;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
