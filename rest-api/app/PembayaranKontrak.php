<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class PembayaranKontrak extends Model
{
    protected $table = 'pembayaran_kontrak';
	protected $primaryKey = 'id_pembayaran_kontrak';
    public $incrementing = true;
    public $timestamps = false;

    protected $fillable = [
        'id_pembayaran_kontrak',
        'id_lapak',
        'tanggal_bayar',
        'tanggal_kontrak_awal',
        'tanggal_kontrak_akhir',
        'nilai',
        'id_admin',
        'id_manager',
        'tanggal_penyerahan'
    ];

    public function lapak(){
        return $this->belongsTo(Lapak::class, 'id_lapak', 'id_lapak');
    }
}
