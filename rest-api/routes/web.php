<?php

/** @var \Laravel\Lumen\Routing\Router $router */

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

// $router->group([
//     'middleware' => 'auth'
// ], function($router){
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

$router->group([
    'prefix' => 'pembayaran-kontrak'
], function () use ($router){
    $router->post('/store', [
        'as' => 'pembayaran-kontrak.index',
        'uses' => 'PembayaranKontrak@store'
    ]);
});
// });
$router->get('/', function () use ($router) {
    return $router->app->version();
});
