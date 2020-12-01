<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreatePembayaranKontrakTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pembayaran_kontrak', function (Blueprint $table) {
            $table->increments('id_pembayaran_kontrak')->unsigned();
            $table->integer('id_lapak')->unsigned();
            $table->dateTime('tanggal_bayar');
            $table->dateTime('tanggal_kontrak_awal');
            $table->dateTime('tanggal_kontrak_akhir');
            $table->integer('nilai');
            $table->integer('id_admin')->unsigned();
            $table->integer('id_manager')->unsigned();
            $table->dateTime('tanggal_penyerahan');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('pembayaran_kontrak');
    }
}
