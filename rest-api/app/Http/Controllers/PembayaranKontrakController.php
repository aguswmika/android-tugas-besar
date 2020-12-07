<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Lapak;
use App\PembayaranKontrak;
use Exception;
use Illuminate\Database\QueryException as DatabaseQueryException;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Validator;

class PembayaranKontrakController extends Controller
{
    public function store(Request $request){
        $valid = Validator::make($request->all(), [
            'id_lapak'              => 'required|integer',
            'tanggal_bayar'         => 'required',
            'tanggal_kontrak_awal'  => 'required',
            'tanggal_kontrak_akhir' => 'required',
            'nilai'                 => 'required|integer',
            'id_admin'              => 'required|integer',
            'id_manager'            => 'required|integer',
            'tanggal_penyerahan'    => 'required'
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
            $dataForInsert = [
                'id_lapak'              => $request->id_lapak,
                'tanggal_bayar'         => $request->tanggal_bayar,
                'tanggal_kontrak_awal'  => $request->tanggal_kontrak_awal,
                'tanggal_kontrak_akhir' => $request->tanggal_kontrak_akhir,
                'nilai'                 => $request->nilai,
                'id_admin'              => $request->id_admin,
                'id_manager'            => $request->id_manager,
                'tanggal_penyerahan'    => $request->tanggal_penyerahan
            ];
            // dd($dataForInsert);
            PembayaranKontrak::create($dataForInsert);

            DB::commit();
            return $this->sendData('Berhasil menambahkan data');
        } catch (Exception | DatabaseQueryException $e) {
            DB::rollBack();
            return $this->sendError('Ada Kesalahan');
        }

    }
    public function get_lapak(Request $request)
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
        try{
            $data = PembayaranKontrak::where('lapak_id', '=', $request->lapak_id)
                                        ->get()
                                        ->toArray();
        DB::commit();
        return $this->sendData($data);
        }catch(Exception | DatabaseQueryException $e){
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
        try{
            $data = Lapak::where('lapak_name', 'like', '%'.$request->nama_lapak.'%')
                        ->get()
                        ->toArray();
        DB::commit();
        return $this->sendData($data);
        }catch(Exception | DatabaseQueryException $e){
            DB::rollBack();
            return $this->sendError($e->getMessage());
        }
    }
}
