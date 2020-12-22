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
    public function index(Request $request)
    {
        // $valid = Validator::make($request->all(), [
        //     'id_lapak'              => 'required|integer',
        //     'tanggal_kontrak_awal'  => 'required',
        //     'tanggal_kontrak_akhir' => 'required',
        //     'nilai'                 => 'required|integer',
        //     // 'id_admin'              => 'required|integer',
        //     // 'id_manager'            => 'required|integer',
        //     // 'tanggal_penyerahan'    => 'required'
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
            // $dataForInsert = [
            //     'id_lapak'              => $request->id_lapak,
            //     'tanggal_bayar'         => date('Y-m-d H:i:s'),
            //     'tanggal_kontrak_awal'  => date('Y-m-d H:i:s', strtotime($request->tanggal_kontrak_awal)),
            //     'tanggal_kontrak_akhir' => date('Y-m-d H:i:s', strtotime($request->tanggal_kontrak_akhir)),
            //     'nilai'                 => $request->nilai,
            //     'id_admin'              => 1,
            //     'id_manager'            => NULL,
            //     'tanggal_penyerahan'    => NULL
            // ];
            // dd($dataForInsert);
            $datas = PembayaranKontrak::orderBy('id_pembayaran_kontrak', 'DESC')
                        ->get();
            $newData = [];
            foreach($datas as $data){
                array_push($newData, [
                    "id_pembayaran_kontrak" => $data->id_pembayaran_kontrak,
                    "id_lapak" => $data->id_lapak,
                    "tanggal_bayar"=> $data->tanggal_bayar,
                    "tanggal_kontrak_awal" => date('d-m-Y', strtotime($data->tanggal_kontrak_awal)),
                    "tanggal_kontrak_akhir" => date('d-m-Y', strtotime($data->tanggal_kontrak_akhir)),
                    "nilai" => $data->nilai,
                    "id_admin" => $data->id_admin,
                    "id_manager" => $data->id_manager,
                    "tanggal_penyerahan" => $data->id_manager,
                    'posisi_lapak' => $data->lapak->posisi_lapak,
                    'nama_lapak' => $data->lapak->nama_lapak,
                    'nama_pemilik' => $data->lapak->nama_pemilik
                ]);
            }

            DB::commit();
            return $this->sendData($newData, 'success');
        } catch (Exception | DatabaseQueryException $e) {
            DB::rollBack();
            return $this->sendData(null, $e->getMessage(), true);
        }
    }
    public function store(Request $request){
        $valid = Validator::make($request->all(), [
            'id_lapak'              => 'required|integer',
            'nilai'                 => 'required|integer',
            'periode'               => 'required|integer',
            // 'id_admin'              => 'required|integer',
            // 'id_manager'            => 'required|integer',
            // 'tanggal_penyerahan'    => 'required'
        ]);

        if ($valid->fails()) {
            $message = '';
            foreach ($valid->errors()->all() as $error) {
                $message .= $error . PHP_EOL;
            }
            return $this->sendData(null, $message, true);
        }

        DB::beginTransaction();
        try {

            if($request->nilai <= 0){
                throw new Exception("Nominal harus diatas 0");
            }
            $tanggal_kontrak_awal = Lapak::where('id_lapak','=',$request->id_lapak)
                                        ->select('tanggal_akhir_kontrak')
                                        ->first();
            
            $time_plus = '+'.$request->periode.' months';
            $tanggal_kontrak_akhir = date('Y-m-d H:i:s', strtotime($time_plus,strtotime($tanggal_kontrak_awal['tanggal_akhir_kontrak'])));
            $dataForInsert = [
                'id_lapak'              => $request->id_lapak,
                'tanggal_bayar'         => date('Y-m-d H:i:s'),
                'tanggal_kontrak_awal'  => date('Y-m-d H:i:s', strtotime($tanggal_kontrak_awal['tanggal_akhir_kontrak'])),
                'tanggal_kontrak_akhir' => date('Y-m-d H:i:s', strtotime($tanggal_kontrak_akhir)),
                'nilai'                 => $request->nilai,
                'id_admin'              => 1,
                'id_manager'            => NULL,
                'tanggal_penyerahan'    => NULL
            ];
            // dd($dataForInsert);
            PembayaranKontrak::create($dataForInsert);

            DB::commit();
            return $this->sendData(null, 'Berhasil menambahkan data');
        } catch (Exception | DatabaseQueryException $e) {
            DB::rollBack();
            return $this->sendData(null, $e->getMessage(), true);
        }

    }

    public function update(Request $request){
        $valid = Validator::make($request->all(), [
            'id_pembayaran_kontrak' => 'required|integer',
            'nilai'                 => 'required|integer',
            'periode'               => 'required|integer',
        ]);

        if ($valid->fails()) {
            $message = '';
            foreach ($valid->errors()->all() as $error) {
                $message .= $error . PHP_EOL;
            }
            return $this->sendData(null, $message, true);
        }

        DB::beginTransaction();
        try {
            $pembayaran_kontrak = PembayaranKontrak::where('id_pembayaran_kontrak','=',$request->id_pembayaran_kontrak)
                                                    ->first();
            $tanggal_kontrak_awal = $pembayaran_kontrak['tanggal_kontrak_awal'];
            
            $time_plus = '+'.$request->periode.' months';
            $tanggal_kontrak_akhir = date('Y-m-d H:i:s', strtotime($time_plus,strtotime($tanggal_kontrak_awal)));
            $dataForUpdate = [
                'tanggal_kontrak_akhir' => date('Y-m-d H:i:s', strtotime($tanggal_kontrak_akhir)),
                'nilai'                 => $request->nilai,
            ];
            $pembayaran_kontrak->update($dataForUpdate);

            DB::commit();
            return $this->sendData($dataForUpdate,'Berhasil mengupdate data');
        } catch (Exception | DatabaseQueryException $e) {
            DB::rollBack();
            return $this->sendData(null, $e->getMessage(), true);
        }

    }
    public function delete(Request $request){
        $valid = Validator::make($request->all(), [
            'id_pembayaran_kontrak' => 'required|integer',
        ]);

        if ($valid->fails()) {
            $message = '';
            foreach ($valid->errors()->all() as $error) {
                $message .= $error . PHP_EOL;
            }
            return $this->sendData(null, $message, true);
        }

        DB::beginTransaction();
        try {
            $pembayaran_kontrak = PembayaranKontrak::where('id_pembayaran_kontrak','=',$request->id_pembayaran_kontrak)
                                                    ->first();
            $pembayaran_kontrak->delete();

            DB::commit();
            return $this->sendData($pembayaran_kontrak,'Berhasil menghapus data');
        } catch (Exception | DatabaseQueryException $e) {
            DB::rollBack();
            return $this->sendData(null, $e->getMessage(), true);
        }

    }
}
