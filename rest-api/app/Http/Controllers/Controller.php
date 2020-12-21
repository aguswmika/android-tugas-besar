<?php

namespace App\Http\Controllers;

use Laravel\Lumen\Routing\Controller as BaseController;

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
}
