package id.aguswmika.pembayarankontraklapak.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.List;

import id.aguswmika.pembayarankontraklapak.MainActivity;
import id.aguswmika.pembayarankontraklapak.PembayaranCreateActivity;
import id.aguswmika.pembayarankontraklapak.R;
import id.aguswmika.pembayarankontraklapak.model.Lapak;

public class LapakAdapter extends RecyclerView.Adapter<LapakAdapter.ViewHolder> {
    private List<Lapak> datas;
    public LapakAdapter(List<Lapak> datas) {
        this.datas = datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaText, pemilikText, posisiText;
        public CardView lapakCardView;

        public ViewHolder(View view) {
            super(view);

            namaText = view.findViewById(R.id.namaText);
            pemilikText = view.findViewById(R.id.pemilikText);
            posisiText = view.findViewById(R.id.posisiText);
            lapakCardView = view.findViewById(R.id.lapakCardView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mahasiswaView = inflater.inflate(R.layout.component_lapak_item, parent, false);

        return new ViewHolder(mahasiswaView);
    }

    @Override
    public void onBindViewHolder(LapakAdapter.ViewHolder holder, int position) {
        Lapak data = datas.get(position);

        TextView namaText = holder.namaText;
        namaText.setText(data.getNamaLapak());
        TextView pemilikText = holder.pemilikText;
        pemilikText.setText(data.getNamaPemilik());
        TextView posisiText = holder.posisiText;
        posisiText.setText(data.getPosisiLapak());
        holder.lapakCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PembayaranCreateActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(intent);
            }
        });



//        if(TextUtils.isEmpty(data.getFotoPemilik())){
//            new DownloadImageTask(fotoImage).execute("https://tripisia.id/assets/images/NoImage.png");
//        }else{
//            new DownloadImageTask(fotoImage).execute(data.getFotoPemilik());
//        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
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
