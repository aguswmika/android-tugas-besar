<?php

/** @var \Laravel\Lumen\Routing\Router $router */

use Illuminate\Support\Facades\Artisan;
use Illuminate\Support\Facades\Hash;

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->group([
    'middleware' => 'auth'
], function($router){
        $router->group([
            'prefix' => 'lapak'
        ], function () use ($router){
            $router->get('/', [
                'as' => 'lapak.index',
                'uses' => 'LapakController@index'
            ]);
            $router->post('/keyword', [
                'as' => 'lapak.index.keyword',
                'uses' => 'LapakController@kategory_lapak_name'
            ]);
        });

    // $router->get('/lapak', [
    //     'as' => 'lapak.index',
    //     'uses' => 'LapakController@store'
    // ]);

    $router->post('/user',[
        'as' => 'user',
        'uses' => 'AuthController@user']);

    $router->group([
        'prefix' => 'pembayaran-kontrak'
    ], function () use ($router){
        $router->post('/index', [
            'as' => 'pembayaran-kontrak.index',
            'uses' => 'PembayaranKontrakController@index'
        ]);
        $router->post('/store', [
            'as' => 'pembayaran-kontrak.store',
            'uses' => 'PembayaranKontrakController@store'
        ]);
        $router->post('/update', [
            'as' => 'pembayaran-kontrak.update',
            'uses' => 'PembayaranKontrakController@update'
        ]);
        $router->post('/delete', [
            'as' => 'pembayaran-kontrak.delete',
            'uses' => 'PembayaranKontrakController@delete'
        ]);
    });
});
$router->get('/', function () use ($router) {
    return $router->app->version();
});

$router->get('/artisan', function () use ($router) {
    Artisan::call('jwt:secret');
    Artisan::call('cache:clear');
});

$router->post('login', 'AuthController@login');
