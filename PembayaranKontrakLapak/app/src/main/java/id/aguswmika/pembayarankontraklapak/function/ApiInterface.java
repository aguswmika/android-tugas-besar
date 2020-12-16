package id.aguswmika.pembayarankontraklapak.function;

import id.aguswmika.pembayarankontraklapak.model.result.LapakResult;
import id.aguswmika.pembayarankontraklapak.model.result.PembayaranKontrakResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/lapak/keyword")
    Call<LapakResult> getLapak(@Field("keyword") String keyword);

    @FormUrlEncoded
    @POST("/pembayaran-kontrak/store")
    Call<PembayaranKontrakResult> addPembayaranKontrak(@Field("id_lapak") int id_lapak,
                                                       @Field("tanggal_kontrak_awal") String tanggal_kontrak_awal,
                                                       @Field("tanggal_kontrak_akhir") String tanggal_kontrak_akhir,
                                                       @Field("nilai") int nilai);
}