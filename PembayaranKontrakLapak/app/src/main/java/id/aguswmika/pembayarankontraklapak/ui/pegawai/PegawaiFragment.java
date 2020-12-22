package id.aguswmika.pembayarankontraklapak.ui.pegawai;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.InputStream;
import java.util.Objects;

import id.aguswmika.pembayarankontraklapak.ContributorActivity;
import id.aguswmika.pembayarankontraklapak.LoginActivity;
import id.aguswmika.pembayarankontraklapak.R;
import id.aguswmika.pembayarankontraklapak.adapter.LapakAdapter;
import id.aguswmika.pembayarankontraklapak.function.ApiClient;
import id.aguswmika.pembayarankontraklapak.function.ApiInterface;
import id.aguswmika.pembayarankontraklapak.function.Session;
import id.aguswmika.pembayarankontraklapak.model.Pegawai;
import id.aguswmika.pembayarankontraklapak.model.result.LapakResult;
import id.aguswmika.pembayarankontraklapak.model.result.PegawaiResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PegawaiFragment extends Fragment {

    ApiInterface apiClient;
    Session session;
    TextView namaLabel, roleLabel, logoutLabel, contributorLabel;
    ImageView foto;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        apiClient = ApiClient.getClient().create(ApiInterface.class);
        session = new Session(requireContext());

        return inflater.inflate(R.layout.fragment_pegawai, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        namaLabel = (TextView) view.findViewById(R.id.namaLabel);
        roleLabel = (TextView) view.findViewById(R.id.roleLabel);
        logoutLabel = (TextView) view.findViewById(R.id.logoutLabel);
        foto = (ImageView) view.findViewById(R.id.imageView);
        contributorLabel = (TextView) view.findViewById(R.id.contributorLabel);

        contributorLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ContributorActivity.class);

                requireActivity().startActivity(intent);

                requireActivity().finish();
            }
        });

        apiClient.getUser("Bearer "+session.read("token")).enqueue(new Callback<PegawaiResult>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<PegawaiResult> call, Response<PegawaiResult> response) {
                assert response.body() != null;
                PegawaiResult result = response.body();
                try{
                    if(result.getError()){
                        Log.d("err", result.getMessage());
                    }else{
                        namaLabel.setText(result.getData().getNamaPegawai());
                        String newRole = result.getData().getRole();
                        roleLabel.setText(newRole.substring(0, 1).toUpperCase() + newRole.substring(1).toLowerCase());
                        if(TextUtils.isEmpty(result.getData().getFoto())){
                            new DownloadImageTask(foto).execute("https://tripisia.id/assets/images/NoImage.png");
                        }else{
                            new DownloadImageTask(foto).execute(result.getData().getFoto());
                        }

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


            @Override
            public void onFailure(Call<PegawaiResult> call, Throwable t) {

            }
        });

        logoutLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.remove("token");
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                requireActivity().startActivity(intent);
                requireActivity().finish();
            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}