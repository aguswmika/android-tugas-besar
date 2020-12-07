package id.aguswmika.pembayarankontraklapak.model;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Lapak {
    @SerializedName("id")
    private int id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("id_kategori_lapak")
    private int id_kategori_lapak;
    @SerializedName("nama_pemilik")
    private String nama_pemilik;
    @SerializedName("alamat_pemilik")
    private String alamat_pemilik;
    @SerializedName("foto_pemilik")
    private String foto_pemilik;
    @SerializedName("posisi_lapak")
    private String posisi_lapak;
    @SerializedName("status")
    private int status;
    @SerializedName("tanggal_pendaftaran")
    private Date tanggal_pendaftaran;
    @SerializedName("tanggal_akhir_kontrak")
    private Date tanggal_akhir_kontrak;

    public Lapak(){ }

    public Lapak(int id, String nama, int id_kategori_lapak, String nama_pemilik, String alamat_pemilik, String foto_pemilik, String posisi_lapak, int status, Date tanggal_pendaftaran, Date tanggal_akhir_kontrak){
        this.id = id;
        this.nama = nama;
        this.id_kategori_lapak = id_kategori_lapak;
        this.nama_pemilik = nama_pemilik;
        this.alamat_pemilik = alamat_pemilik;
        this.foto_pemilik = foto_pemilik;
        this.posisi_lapak = posisi_lapak;
        this.status = status;
        this.tanggal_pendaftaran = tanggal_pendaftaran;
        this.tanggal_akhir_kontrak = tanggal_akhir_kontrak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getIdKategoriLapak() {
        return id_kategori_lapak;
    }

    public void setIdKategoriLapak(int id_kategori_lapak) {
        this.id_kategori_lapak = id_kategori_lapak;
    }

    public String getNamaPemilik() {
        return nama_pemilik;
    }

    public void setNamaPemilik(String nama_pemilik) {
        this.nama_pemilik = nama_pemilik;
    }

    public String getAlamatPemilik() {
        return alamat_pemilik;
    }

    public void setAlamatPemilik(String alamat_pemilik) {
        this.alamat_pemilik = alamat_pemilik;
    }

    public String getFotoPemilik() {
        return foto_pemilik;
    }

    public void setFotoPemilik(String foto_pemilik) {
        this.foto_pemilik = foto_pemilik;
    }

    public String getPosisiLapak() {
        return posisi_lapak;
    }

    public void setPosisiLapak(String posisi_lapak) {
        this.posisi_lapak = posisi_lapak;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTanggalPendaftaran() {
        return tanggal_pendaftaran;
    }

    public void setTanggalPendaftaran(Date tanggal_pendaftaran) {
        this.tanggal_pendaftaran = tanggal_pendaftaran;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SimpleDateFormat")
    public void setTanggalPendaftaran(String tanggal_pendaftaran){
        try{
            this.tanggal_pendaftaran = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(tanggal_pendaftaran);
        }catch (ParseException e){
            Log.e("Date error : ", Objects.requireNonNull(e.getMessage()));
        }
    }

    public Date getTanggalAkhirKontrak() {
        return tanggal_akhir_kontrak;
    }

    public void setTanggalAkhirKontrak(Date tanggal_akhir_kontrak) {
        this.tanggal_akhir_kontrak = tanggal_akhir_kontrak;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SimpleDateFormat")
    public void setTanggalAkhirKontrak(String tanggal_akhir_kontrak){
        try{
            this.tanggal_akhir_kontrak = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(tanggal_akhir_kontrak);
        }catch (ParseException e){
            Log.e("Date error : ", Objects.requireNonNull(e.getMessage()));
        }
    }
}
