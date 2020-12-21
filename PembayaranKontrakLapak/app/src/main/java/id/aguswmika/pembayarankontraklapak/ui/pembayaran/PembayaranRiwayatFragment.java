package id.aguswmika.pembayarankontraklapak.ui.pembayaran;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import id.aguswmika.pembayarankontraklapak.R;
import id.aguswmika.pembayarankontraklapak.adapter.KontrakRiwayatAdapter;
import id.aguswmika.pembayarankontraklapak.adapter.LapakAdapter;
import id.aguswmika.pembayarankontraklapak.function.ApiClient;
import id.aguswmika.pembayarankontraklapak.function.ApiInterface;
import id.aguswmika.pembayarankontraklapak.model.result.LapakResult;
import id.aguswmika.pembayarankontraklapak.model.result.PembayaranKontrakDataResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranRiwayatFragment extends Fragment {
    ApiInterface apiClient;
    KontrakRiwayatAdapter adapter;
    RecyclerView pembayaranKontrakRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        apiClient = ApiClient.getClient().create(ApiInterface.class);

        return inflater.inflate(R.layout.fragment_pembayaran_riwayat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pembayaranKontrakRecycler = view.findViewById(R.id.pembayaranKontrakRecycler);
        pembayaranKontrakRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));

        apiClient.getPembayaran().enqueue(new Callback<PembayaranKontrakDataResult>() {
            @Override
            public void onResponse(Call<PembayaranKontrakDataResult> call, Response<PembayaranKontrakDataResult> response) {
                adapter = new KontrakRiwayatAdapter(response.body().getData());
                pembayaranKontrakRecycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PembayaranKontrakDataResult> call, Throwable t) {

            }
        });
    }
}