<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeyToLapakTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('lapak', function (Blueprint $table) {
            $table->foreign('id_kategori_lapak')->references('id_kategori_lapak')->on('kategori_lapak');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('lapak', function (Blueprint $table) {
            //
        });
    }
}
