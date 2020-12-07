package id.aguswmika.sipasar.ui.pembayaran;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KontrakViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KontrakViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}