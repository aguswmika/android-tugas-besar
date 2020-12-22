package id.aguswmika.pembayarankontraklapak.model.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.aguswmika.pembayarankontraklapak.model.Pegawai;

public class PegawaiResult {
    @SerializedName("data")
    private Pegawai data;
    @SerializedName("error")
    private Boolean error;
    @SerializedName("message")
    private String message;

    public void setData(Pegawai data) {
        this.data = data;
    }

    public Pegawai getData() {
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
