package id.aguswmika.pembayarankontraklapak.ui.pembayaran;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import id.aguswmika.pembayarankontraklapak.R;

public class PembayaranRiwayatFragment extends Fragment {

    private PembayaranViewModel pembayaranViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pembayaranViewModel =
                ViewModelProviders.of(this).get(PembayaranViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pembayaran_riwayat, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        pembayaranViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}