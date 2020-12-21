<?php

namespace App\Http\Controllers;

use App\Admin;
use App\KategoriLapak;
use App\Lapak;
use App\PembayaranKontrak;
use Exception;
use Illuminate\Database\QueryException;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\Auth;
use Tymon\JWTAuth\Facades\JWTAuth;

class AuthController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }

    public function login(Request $request)
    {
        $valid = Validator::make($request->all(), [
            'username'          => 'required|string',
            'password'          => 'required|string',
        ]);

        if ($valid->fails()) {
            $message = '';
            foreach ($valid->errors()->all() as $error) {
                $message .= $error . PHP_EOL;
            }
            return $this->sendData(null, $message, true);
        }
        $user = Admin::where('username', '=',$request->username)->first();
        if (!$user) {

            return $this->sendData(null, "User tidak ditemukan !", true);
        }
        if ($request->password == $user->password) {
            if (! $token = Auth::fromuser($user)) {
                return $this->sendData(null, 'Unauthorize', true);
            }
            return $this->respondWithToken($token);
        }
        return $this->sendData(null, 'Username atau password salah', true);
        
    }
}
