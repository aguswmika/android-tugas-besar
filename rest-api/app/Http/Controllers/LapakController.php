<?php

namespace App\Http\Controllers;

use App\KategoriLapak;
use App\Lapak;
use App\PembayaranKontrak;
use Exception;
use Illuminate\Database\QueryException;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Validator;

class LapakController extends Controller
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

    public function index(Request $request)
    {
        $valid = Validator::make($request->all(), [
            'id_lapak'          => 'required|integer',
        ]);
        if ($valid->fails()) {
            $message = '';
            foreach ($valid->errors()->all() as $error) {
                $message .= $error . PHP_EOL;
            }
            return $this->sendError($message);
        }

        DB::beginTransaction();
        try {
            $data = Lapak::where('id_lapak', '=', $request->id_lapak)
                        ->get();
            dd($data);
            DB::commit();
            return $this->sendData($data);
        } catch (Exception | QueryException $e) {
            DB::rollBack();
            return $this->sendError($e->getMessage());
        }
    }
    public function get_lapak_by_name(Request $request)
    {
        $valid = Validator::make($request->all(), [
            'nama_lapak'          => 'required|string',
        ]);

        if ($valid->fails()) {
            $message = '';
            foreach ($valid->errors()->all() as $error) {
                $message .= $error . PHP_EOL;
            }
            return $this->sendError($message);
        }

        DB::beginTransaction();
        try {
            $data = Lapak::where('nama_lapak', 'like', '%' . $request->nama_lapak . '%')
                ->get()
                ->toArray();
            DB::commit();
            return $this->sendData($data);
        } catch (Exception | QueryException $e) {
            DB::rollBack();
            return $this->sendError($e->getMessage());
        }
    }
    public function kategory_lapak_name(Request $request)
    {
        // $valid = Validator::make($request->all(), [
        //     'keyword'          => 'required|string',
        // ]);

        // if ($valid->fails()) {
        //     $message = '';
        //     foreach ($valid->errors()->all() as $error) {
        //         $message .= $error . PHP_EOL;
        //     }
        //     return $this->sendError($message);
        // }

        DB::beginTransaction();
        try {
            $data = [];
            if(!empty($request->keyword)){
                $data = Lapak::where('lapak.nama_lapak', 'like', '%' . $request->keyword . '%')
                            ->orWhere('lapak.posisi_lapak','like', '%' . $request->keyword . '%')
                            ->with('kategori')
                            ->get();
            }

            
            DB::commit();
            return $this->sendData($data);
            
        } catch (Exception | QueryException $e) {
            DB::rollBack();
            return $this->sendError($e->getMessage());
        }
    }
}
