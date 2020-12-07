<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateLapakTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('lapak', function (Blueprint $table) {
            $table->increments('id_lapak');
            $table->string('nama_lapak',20);
            $table->integer('id_kategory_lapak')->unsigned();
            $table->string('nama_pemilik',20);
            $table->text('alamat');
            $table->text('foto_pemilik');
            $table->string('posisi_lapak',5);
            $table->integer('status')->comment('1=aktif, 0=nonaktif');
            $table->dateTime('tanggal_pendaftaran');
            $table->dateTime('tanggal_akhir_kontrak');
            $table->integer('id_admin')->unsigned();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('lapak');
    }
}
