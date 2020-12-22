package id.aguswmika.pembayarankontraklapak.ui.pembayaran;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.aguswmika.pembayarankontraklapak.R;
import id.aguswmika.pembayarankontraklapak.adapter.KontrakRiwayatAdapter;
import id.aguswmika.pembayarankontraklapak.function.ApiClient;
import id.aguswmika.pembayarankontraklapak.function.ApiInterface;
import id.aguswmika.pembayarankontraklapak.function.Session;
import id.aguswmika.pembayarankontraklapak.model.result.PembayaranKontrakResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranRiwayatFragment extends Fragment {
    ApiInterface apiClient;
    KontrakRiwayatAdapter adapter;
    RecyclerView pembayaranKontrakRecycler;
    Session session;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        session = new Session(requireContext());
        apiClient = ApiClient.getClient().create(ApiInterface.class);

        return inflater.inflate(R.layout.fragment_pembayaran_riwayat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pembayaranKontrakRecycler = view.findViewById(R.id.pembayaranKontrakRecycler);
        pembayaranKontrakRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));

        apiClient.getPembayaran().enqueue(new Callback<PembayaranKontrakResult>() {
            @Override
            public void onResponse(Call<PembayaranKontrakResult> call, Response<PembayaranKontrakResult> response) {
                PembayaranKontrakResult result = response.body();
                try{
                    if(result.getError()){
                        Log.d("err", result.getMessage());
                    }else{
                        adapter = new KontrakRiwayatAdapter(result.getData());
                        pembayaranKontrakRecycler.setAdapter(adapter);
                    }
                }catch (Exception e){
                    Log.e("err", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<PembayaranKontrakResult> call, Throwable t) {

            }
        });
    }
}