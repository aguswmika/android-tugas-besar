package id.aguswmika.pembayarankontraklapak.model;

import com.google.gson.annotations.SerializedName;

public class PembayaranLapak {
    @SerializedName("id_pembayaran_kontrak")
    private int id_pembayaran_kontrak;
    @SerializedName("id_lapak")
    private int id_lapak;
    @SerializedName("tanggal_bayar")
    private String tanggal_bayar;
    @SerializedName("tanggal_kontrak_awal")
    private String tanggal_kontrak_awal;
    @SerializedName("tanggal_kontrak_akhir")
    private String tanggal_kontrak_akhir;
    @SerializedName("nilai")
    private int nilai;
    @SerializedName("foto_pemilik")
    private String foto_pemilik;
    @SerializedName("id_admin")
    private String id_admin;
    @SerializedName("id_manager")
    private String id_manager;
    @SerializedName("tanggal_penyerahan")
    private String tanggal_penyerahan;
    @SerializedName("posisi_lapak")
    private String posisi_lapak;
    @SerializedName("nama_lapak")
    private String nama_lapak;
    @SerializedName("nama_pemilik")
    private String nama_pemilik;

    public String getTanggalBayar() {
        return tanggal_bayar;
    }

    public String getTanggalKontrakAwal() {
        return tanggal_kontrak_awal;
    }

    public String getTanggalKontrakAkhir() {
        return tanggal_kontrak_akhir;
    }

    public String getNamaLapak() {
        return nama_lapak;
    }

    public String getNamaPemilik() {
        return nama_pemilik;
    }

    public String getPosisiLapak() {
        return posisi_lapak;
    }
}
