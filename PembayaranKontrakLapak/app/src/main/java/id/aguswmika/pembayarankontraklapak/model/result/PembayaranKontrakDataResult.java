package id.aguswmika.pembayarankontraklapak.model.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.aguswmika.pembayarankontraklapak.model.Lapak;
import id.aguswmika.pembayarankontraklapak.model.PembayaranLapak;

public class PembayaranKontrakDataResult {
    @SerializedName("data")
    private List<PembayaranLapak> data;
    @SerializedName("error")
    private Boolean error;

    public void setData(List<PembayaranLapak> data) {
        this.data = data;
    }

    public List<PembayaranLapak> getData() {
        return data;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getError() {
        return error;
    }
}
