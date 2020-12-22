package id.aguswmika.pembayarankontraklapak.function;

import id.aguswmika.pembayarankontraklapak.model.result.LapakResult;
import id.aguswmika.pembayarankontraklapak.model.result.LoginResult;
import id.aguswmika.pembayarankontraklapak.model.result.PembayaranKontrakResult;
import id.aguswmika.pembayarankontraklapak.model.result.PembayaranKontrakResultTemp;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("lapak/keyword")
    Call<LapakResult> getLapak(@Field("keyword") String keyword, @Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("pembayaran-kontrak/store")
    Call<PembayaranKontrakResult> addPembayaranKontrak(@Field("id_lapak") int id_lapak,
                                                           @Field("nilai") int nilai,
                                                           @Field("periode") int periode,
                                                           @Header("Authorization") String auth);

    @POST("pembayaran-kontrak/index")
    Call<PembayaranKontrakResult> getPembayaran();

    @FormUrlEncoded
    @POST("login")
    Call<LoginResult> login(@Field("username") String username,
                            @Field("password") String password);
}