package id.aguswmika.pembayarankontraklapak.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import id.aguswmika.pembayarankontraklapak.LoginActivity;
import id.aguswmika.pembayarankontraklapak.MainActivity;
import id.aguswmika.pembayarankontraklapak.R;
import id.aguswmika.pembayarankontraklapak.adapter.KontrakRiwayatAdapter;
import id.aguswmika.pembayarankontraklapak.adapter.LapakAdapter;
import id.aguswmika.pembayarankontraklapak.function.ApiClient;
import id.aguswmika.pembayarankontraklapak.function.ApiInterface;
import id.aguswmika.pembayarankontraklapak.function.Session;
import id.aguswmika.pembayarankontraklapak.model.Lapak;
import id.aguswmika.pembayarankontraklapak.model.result.LapakResult;
import id.aguswmika.pembayarankontraklapak.model.result.PembayaranKontrakResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ApiInterface apiClient;
    LapakAdapter adapter;
    RecyclerView lapakRecycler;
    Session session;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        session = new Session(requireContext());
        apiClient = ApiClient.getClient().create(ApiInterface.class);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void handleSearch(String keyword){
        apiClient.getLapak(keyword, "Bearer "+session.read("token")).enqueue(new Callback<LapakResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<LapakResult> call, Response<LapakResult> response) {
                assert response.body() != null;
                LapakResult result = response.body();
                try{
                    if(result.getError()){
                        Log.d("err", result.getMessage());
                    }else{
                        adapter = new LapakAdapter(result.getData());
                        lapakRecycler.setAdapter(adapter);
                    }
                }catch (Exception e){
                    Log.e("err", Objects.requireNonNull(e.getMessage()));
                    if(response.code() == 401){
                        Toast.makeText(requireContext(), "Akses ditolak, silahkan login!", Toast.LENGTH_SHORT).show();


                        session.remove("token");

                        Intent intent = new Intent(requireContext(), LoginActivity.class);
                        requireContext().startActivity(intent);

                        Objects.requireNonNull(getActivity()).finish();
                    }
                }
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

        searchView.clearFocus();
        searchView.setActivated(false);
        searchView.setQueryHint("Ketik untuk mencari lapak");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);

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