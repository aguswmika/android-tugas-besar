package id.aguswmika.pembayarankontraklapak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import id.aguswmika.pembayarankontraklapak.function.ApiClient;
import id.aguswmika.pembayarankontraklapak.function.ApiInterface;
import id.aguswmika.pembayarankontraklapak.function.Session;
import id.aguswmika.pembayarankontraklapak.model.result.PembayaranKontrakResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranCreateActivity extends AppCompatActivity {
    private EditText tanggalText, tanggalBerakhirText, nominalText;
    private Spinner periodeSpinner;
    private int id_lapak;
    private String tanggal_akhir_kontrak;
    private int total;
    private int periode;
    private TextView tanggalLabel;
    ApiInterface apiClient;
    Session session;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_create);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_lapak = extras.getInt("id_lapak");
            tanggal_akhir_kontrak = extras.getString("tanggal_akhir_kontrak");
        }else{
            onBackPressed();
        }

        session = new Session(PembayaranCreateActivity.this);
        apiClient = ApiClient.getClient().create(ApiInterface.class);

        periodeSpinner = (Spinner) findViewById(R.id.periodeSpinner);
        tanggalLabel = (TextView) findViewById(R.id.tanggalLabel);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try{
            tanggalLabel.setText(formatter.format(Objects.requireNonNull(formatter.parse(tanggal_akhir_kontrak))));
        }catch (Exception e){
            e.printStackTrace();
        }
        periodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        nominalText = (EditText) findViewById(R.id.nominalText);
        nominalText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateTotal();
            }
        });


        Button bayarButton = (Button) findViewById(R.id.bayarButton);

        bayarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PembayaranKontrakResult> addPembayaranKontrak = apiClient.addPembayaranKontrak(
                        id_lapak,
                        total,
                        periode,
                        "Bearer "+session.read("token")
                );

                addPembayaranKontrak.enqueue(new Callback<PembayaranKontrakResult>() {
                    @Override
                    public void onResponse(Call<PembayaranKontrakResult> call, Response<PembayaranKontrakResult> response) {
                        PembayaranKontrakResult result = response.body();
                        try{
                            if(result.getError()){
                                Log.d("err", result.getMessage());
                            }else{
                                PembayaranCreateActivity.super.onBackPressed();
                                Toast.makeText(PembayaranCreateActivity.this, result.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        }catch (Exception e){
                            if(response.code() == 401){
                                Toast.makeText(PembayaranCreateActivity.this, "Akses ditolak, silahkan login!", Toast.LENGTH_SHORT).show();


                                session.remove("token");

                                Intent intent = new Intent(PembayaranCreateActivity.this, LoginActivity.class);
                                startActivity(intent);

                                finish();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<PembayaranKontrakResult> call, Throwable t) {
                        Log.e("e", t.getMessage());
                    }
                });
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    private void updateTotal() {
        int add_month = 0;
        switch (periodeSpinner.getSelectedItemPosition()){
            case 0:
                add_month = 1;
                break;
            case 1:
                add_month = 6;
                break;
            case 2:
                add_month = 12;
                break;
            case 3:
                add_month = 24;
                break;
        }

        periode = add_month;
        final Calendar myCalendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        Date dateFormat = null;
        try{
            dateFormat = formatter.parse(tanggal_akhir_kontrak);
        }catch (Exception e){
            Log.e("err", e.getMessage());
        }

        myCalendar.setTime(dateFormat);
        myCalendar.add(Calendar.MONTH, add_month);

        try {
            tanggalLabel.setText(formatter.format(myCalendar.getTime()));
        }catch (Exception e){
            e.printStackTrace();
        }

        int nominal = Integer.parseInt(TextUtils.isEmpty(nominalText.getText().toString()) ? "0" : nominalText.getText().toString());
        total = nominal * add_month;

        TextView totalLabel = (TextView) findViewById(R.id.totalLabel);

        totalLabel.setText("Total: Rp "+ total);
    }
}