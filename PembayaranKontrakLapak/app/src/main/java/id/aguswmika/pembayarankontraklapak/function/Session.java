package id.aguswmika.pembayarankontraklapak.function;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private SharedPreferences sharedPref;
    private String SHARE_PREF_NAME = "sipasar_pref";

    public Session(Context context){
        sharedPref = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void write(String key, String value){
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public void remove(String key){
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.remove(key);
        editor.apply();
    }

    public String read(String key){
        return sharedPref.getString(key, null);
    }
}
