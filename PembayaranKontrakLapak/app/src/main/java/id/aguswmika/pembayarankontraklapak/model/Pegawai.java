package id.aguswmika.pembayarankontraklapak.model;

import com.google.gson.annotations.SerializedName;

public class Pegawai {
    @SerializedName("id_admin")
    private int id_admin;
    @SerializedName("id_pegawai")
    private int id_pegawai;
    @SerializedName("username")
    private String username;
    @SerializedName("role")
    private String role;
    @SerializedName("nama_pegawai")
    private String nama_pegawai;
    @SerializedName("foto")
    private String foto;


    public int getIdAdmin() {
        return id_admin;
    }

    public int getIdPegawai() {
        return id_pegawai;
    }

    public String getUsername() {
        return username;
    }

    public String getNamaPegawai() {
        return nama_pegawai;
    }

    public String getFoto() {
        return foto;
    }

    public String getRole() {
        return role;
    }
}
