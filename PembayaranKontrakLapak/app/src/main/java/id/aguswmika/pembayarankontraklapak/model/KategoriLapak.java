package id.aguswmika.pembayarankontraklapak.model;

import com.google.gson.annotations.SerializedName;

public class KategoriLapak {
    @SerializedName("id_kategori_lapak")
    private int id_kategori_lapak;
    @SerializedName("nama_kategori")
    private String nama_kategori;

    public void setIdKategoriLapak(int id_kategori_lapak) {
        this.id_kategori_lapak = id_kategori_lapak;
    }

    public void setNamaKategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }
}

