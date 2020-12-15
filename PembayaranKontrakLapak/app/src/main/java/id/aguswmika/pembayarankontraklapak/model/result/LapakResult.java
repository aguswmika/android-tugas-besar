package id.aguswmika.pembayarankontraklapak.model.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.aguswmika.pembayarankontraklapak.model.Lapak;

public class LapakResult {
    @SerializedName("data")
    private List<Lapak> data;
    @SerializedName("error")
    private Boolean error;

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
}
