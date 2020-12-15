package id.aguswmika.pembayarankontraklapak.function;

import id.aguswmika.pembayarankontraklapak.model.result.LapakResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("/lapak/keyword")
    Call<LapakResult> getLapak(@Field("keyword") String keyword);
}