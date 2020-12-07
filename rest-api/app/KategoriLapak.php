<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class KategoriLapak extends Model
{
    protected $table = 'kategori_lapak';
	protected $primaryKey = 'id_kategori_lapak';
    public $incrementing = true;

    protected $fillable = [
        'id_kategori_lapak',
        'nama_kategori_lapak',
    ];
}
