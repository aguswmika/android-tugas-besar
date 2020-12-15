package id.aguswmika.pembayarankontraklapak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

import id.aguswmika.pembayarankontraklapak.function.ApiClient;
import id.aguswmika.pembayarankontraklapak.function.ApiInterface;
import id.aguswmika.pembayarankontraklapak.model.result.PembayaranKontrakResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranCreateActivity extends AppCompatActivity {
    private EditText tanggalText, tanggalBerakhirText, nominalText;
    private Spinner periodeSpinner;
    private int id_lapak;
    private int total;

    ApiInterface apiClient;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_create);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_lapak = extras.getInt("id_lapak");
        }else{
            onBackPressed();
        }

        apiClient = ApiClient.getClient().create(ApiInterface.class);

        final Calendar myCalendar = Calendar.getInstance();

        tanggalText = (EditText) findViewById(R.id.tanggalText);
        tanggalBerakhirText = (EditText) findViewById(R.id.tanggalBerakhirText);
        periodeSpinner = (Spinner) findViewById(R.id.periodeSpinner);
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
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                tanggalText.setText(sdf.format(myCalendar.getTime()));


                Calendar tanggal_berakhir = Calendar.getInstance();
                tanggal_berakhir.setTime(myCalendar.getTime());
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
                tanggal_berakhir.add(Calendar.MONTH, add_month);
                tanggalBerakhirText.setText(sdf.format(tanggal_berakhir));
            }
        };

        tanggalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PembayaranCreateActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                updateTotal();
            }
        });

        Button bayarButton = (Button) findViewById(R.id.bayarButton);

        bayarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PembayaranKontrakResult> addPembayaranKontrak = apiClient.addPembayaranKontrak(
                        id_lapak,
                        tanggalText.getText().toString(),
                        tanggalBerakhirText.getText().toString(),
                        total
                );

                addPembayaranKontrak.enqueue(new Callback<PembayaranKontrakResult>() {
                    @Override
                    public void onResponse(Call<PembayaranKontrakResult> call, Response<PembayaranKontrakResult> response) {
//                        assert response.body() != null;
//
//                        if(response.body().getError()){
//                            Log.e("e", response.body().getMessage());
//                        }else{
//                            Log.d("b", response.body().getData());
//                        }
//
                        PembayaranCreateActivity.super.onBackPressed();
                        Toast.makeText(PembayaranCreateActivity.this, "Berhasil",Toast.LENGTH_LONG ).show();

                    }

                    @Override
                    public void onFailure(Call<PembayaranKontrakResult> call, Throwable t) {
                        Log.e("e", t.getMessage());
                    }
                });
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateTotal(){
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

        int nominal = Integer.parseInt(TextUtils.isEmpty(nominalText.getText().toString()) ? "0" : nominalText.getText().toString());
        total = nominal * add_month;

        TextView totalLabel = (TextView) findViewById(R.id.totalLabel);

        totalLabel.setText("Total: Rp "+ total);
    }
}