<?php

namespace App\Http\Controllers;

use Laravel\Lumen\Routing\Controller as BaseController;
use Illuminate\Support\Facades\Auth;

class Controller extends BaseController
{
    public function sendData($data, $message = '', $error = false){
        return response([
            'data' => $data,
            'message' => $message,
            'error' => false
        ], 200);
    }
    
    // public function sendError($message, $status = 400)
    // {
    //     return response([
    //         'message' => $message,
    //         'error' => true
    //     ], $status);
    // }

    protected function respondWithToken($token)
    {
        return $this->sendData([
            'token' => $token,
            'token_type' => 'bearer',
            'expires_in' => Auth::factory()->getTTL() * 60
        ], 'Success');
    }

}
