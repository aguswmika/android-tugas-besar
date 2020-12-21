package id.aguswmika.pembayarankontraklapak.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

import id.aguswmika.pembayarankontraklapak.PembayaranCreateActivity;
import id.aguswmika.pembayarankontraklapak.R;
import id.aguswmika.pembayarankontraklapak.model.Lapak;
import id.aguswmika.pembayarankontraklapak.model.PembayaranLapak;

public class KontrakRiwayatAdapter extends RecyclerView.Adapter<KontrakRiwayatAdapter.ViewHolder> {
    private List<PembayaranLapak> datas;
    public KontrakRiwayatAdapter(List<PembayaranLapak> datas) {
        this.datas = datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaText, pemilikText, posisiText, periodeText, tanggalText;
        public CardView lapakCardView;

        public ViewHolder(View view) {
            super(view);

            namaText = view.findViewById(R.id.namaText);
            pemilikText = view.findViewById(R.id.pemilikText);
            posisiText = view.findViewById(R.id.posisiText);
            lapakCardView = view.findViewById(R.id.lapakCardView);
            periodeText = view.findViewById(R.id.periodeText);
            tanggalText = view.findViewById(R.id.tanggalText);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mahasiswaView = inflater.inflate(R.layout.component_kontrak_item, parent, false);

        return new ViewHolder(mahasiswaView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(KontrakRiwayatAdapter.ViewHolder holder, int position) {
        final PembayaranLapak data = datas.get(position);

        TextView namaText = holder.namaText;
        namaText.setText(data.getNamaLapak());
        TextView pemilikText = holder.pemilikText;
        pemilikText.setText(data.getNamaPemilik());
        TextView posisiText = holder.posisiText;
        posisiText.setText(data.getPosisiLapak());
//        holder.lapakCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), PembayaranCreateActivity.class);
////                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                intent.putExtra("id_lapak", data.getIdLapak());
//                view.getContext().startActivity(intent);
//            }
//        });
        TextView periodeText = holder.periodeText;
        periodeText.setText(data.getTanggalKontrakAwal() + " s.d "+data.getTanggalKontrakAkhir());

        TextView tanggalText = holder.tanggalText;
        tanggalText.setText(data.getTanggalBayar());



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
}
