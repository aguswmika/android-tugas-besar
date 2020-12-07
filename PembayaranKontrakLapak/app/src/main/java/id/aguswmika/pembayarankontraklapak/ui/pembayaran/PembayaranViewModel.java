package id.aguswmika.pembayarankontraklapak.ui.pembayaran;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PembayaranViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PembayaranViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}