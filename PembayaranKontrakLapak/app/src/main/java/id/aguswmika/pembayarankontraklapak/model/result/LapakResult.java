package id.aguswmika.pembayarankontraklapak.model.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.aguswmika.pembayarankontraklapak.model.Lapak;

public class LapakResult {
    @SerializedName("data")
    private List<Lapak> data;
    @SerializedName("error")
    private Boolean error;
    @SerializedName("message")
    private String message;

    public void setData(List<Lapak> data) {
        this.data = data;
    }

    public List<Lapak> getData() {
        return data;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getError() {
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
