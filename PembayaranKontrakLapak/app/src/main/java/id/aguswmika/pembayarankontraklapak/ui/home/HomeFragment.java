package id.aguswmika.pembayarankontraklapak.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import id.aguswmika.pembayarankontraklapak.R;
import id.aguswmika.pembayarankontraklapak.adapter.LapakAdapter;
import id.aguswmika.pembayarankontraklapak.function.ApiClient;
import id.aguswmika.pembayarankontraklapak.function.ApiInterface;
import id.aguswmika.pembayarankontraklapak.model.Lapak;
import id.aguswmika.pembayarankontraklapak.model.result.LapakResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ApiInterface apiClient;
    LapakAdapter adapter;
    RecyclerView lapakRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        apiClient = ApiClient.getClient().create(ApiInterface.class);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void handleSearch(String keyword){
        apiClient.getLapak(keyword).enqueue(new Callback<LapakResult>() {
            @Override
            public void onResponse(Call<LapakResult> call, Response<LapakResult> response) {
                assert response.body() != null;
                adapter = new LapakAdapter(response.body().getData());
                lapakRecycler.setAdapter(adapter);

//                Log.d("Res", String.valueOf(response.body().getData().size()));
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onFailure(Call<LapakResult> call, Throwable t) {
                Log.e("Err", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lapakRecycler = view.findViewById(R.id.lapakRecycler);
        lapakRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));

        SearchView searchView = view.findViewById(R.id.lapakSearch);

        searchView.setActivated(true);
        searchView.setQueryHint("Type your keyword here");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                handleSearch(s);
                return false;
            }
        });
    }
}