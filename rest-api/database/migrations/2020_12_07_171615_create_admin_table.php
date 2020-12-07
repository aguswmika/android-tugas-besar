<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateAdminTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('admin', function (Blueprint $table) {
            $table->increments('id_admin');
            $table->unsignedInteger('id_pegawai');
            $table->string('username',10);
            $table->string('password',15);
            $table->string('role',7)->comment('ADMIN, MANAGER, PEGAWAI');
            $table->integer('status')->comment('1=aktif, 0=nonaktif');
            
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('admin');
    }
}
