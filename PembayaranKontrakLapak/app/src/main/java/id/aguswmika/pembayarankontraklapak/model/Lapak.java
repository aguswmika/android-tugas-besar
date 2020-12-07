package id.aguswmika.pembayarankontraklapak.model;

import java.util.Date;

public class Lapak {
    private int id;
    private String nama;
    private int id_kategori_lapak;
    private String nama_pemilik;
    private String alamat_pemilik;
    private String foto_pemilik;
    private String posisi_lapak;
    private int status;
    private Date tanggal_pendaftaran;
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

    public Date getTanggalAkhirKontrak() {
        return tanggal_akhir_kontrak;
    }

    public void setTanggalAkhirKontrak(Date tangga_akhir_kontrak) {
        this.tanggal_akhir_kontrak = tangga_akhir_kontrak;
    }
}
