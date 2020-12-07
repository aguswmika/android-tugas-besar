package id.aguswmika.sipasar.ui.pembayaran;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.aguswmika.sipasar.R;

public class KontrakFragment extends Fragment {

    private KontrakViewModel kontrakViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pembayaran_kontrak, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SearchView searchView = view.findViewById(R.id.lapakSearch);

        searchView.setActivated(true);
        searchView.setQueryHint("Type your keyword here");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();
    }
}