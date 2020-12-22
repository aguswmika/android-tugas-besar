package id.aguswmika.pembayarankontraklapak;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.aguswmika.pembayarankontraklapak.function.ApiClient;
import id.aguswmika.pembayarankontraklapak.function.ApiInterface;
import id.aguswmika.pembayarankontraklapak.function.Session;
import id.aguswmika.pembayarankontraklapak.model.result.LoginResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ApiInterface apiClient;
    Button button;
    EditText usernameText, passwordText;
    ProgressBar loading;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}


        setContentView(R.layout.activity_login);
        session = new Session(LoginActivity.this);

        if(!TextUtils.isEmpty(session.read("token"))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }

        apiClient = ApiClient.getClient().create(ApiInterface.class);

        button = (Button) findViewById(R.id.loginButton);

        usernameText = (EditText) findViewById(R.id.usernameText);
        passwordText = (EditText) findViewById(R.id.passwordText);

        loading = (ProgressBar) findViewById(R.id.loading);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                loading.setVisibility(View.VISIBLE);

                apiClient.login(username, password).enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        assert response.body() != null;
                        LoginResult result = response.body();

                        try{
                            if(result.getError()){
                                Toast.makeText(LoginActivity.this, "Kesalahan : "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                session.write("token", result.getData());
                                startActivity(intent);
                                finish();
                            }

                            loading.setVisibility(View.GONE);
                        }catch (Exception e){
                            Log.d("err", e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Log.e("res", t.getMessage());
                    }
                });
            }
        });


    }
}
