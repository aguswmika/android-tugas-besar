<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Lapak extends Model
{
    protected $table = 'lapak';
	protected $primaryKey = 'id_lapak';
    public $incrementing = true;

    protected $fillable = [
        'id_lapak',
        'nama_lapak',
        'id_kategori_lapak',
        'nama_pemilik',
        'alamat_pemilik',
        'foto_pemilik',
        'posisi_lapak',
        'status',
        'tanggal_pendaftaran',
        'tanggal_akhir_kontrak',
        'id_admin'
    ];

    public function kategori(){
        return $this->belongsTo(KategoriLapak::class, 'id_kategori_lapak', 'id_kategori_lapak');
    }
}
