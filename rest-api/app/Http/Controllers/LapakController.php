<?php

namespace App\Http\Controllers;

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
            'lapak_id'          => 'required|integer',
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
            $data = Lapak::where('lapak_id', '=', $request->lapak_id)
                        ->get();

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
            $data = Lapak::where('lapak_name', 'like', '%' . $request->nama_lapak . '%')
                ->get()
                ->toArray();
            DB::commit();
            return $this->sendData($data);
        } catch (Exception | QueryException $e) {
            DB::rollBack();
            return $this->sendError($e->getMessage());
        }
    }
}
