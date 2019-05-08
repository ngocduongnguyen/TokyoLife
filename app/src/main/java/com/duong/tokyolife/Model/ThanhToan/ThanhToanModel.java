package com.duong.tokyolife.Model.ThanhToan;

import android.util.Log;

import com.duong.tokyolife.Model.ObjectClass.ChiTietHoaDon;
import com.duong.tokyolife.Model.ObjectClass.HoaDon;
import com.duong.tokyolife.Utils.Data;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ThanhToanModel {
    public boolean themHoaDon(HoaDon hoaDon){
        boolean ketqua = false;

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hmGoiHam = new HashMap<>();
        hmGoiHam.put("goiham","themHoaDon");

        //lay list chi tiet hoa don
//        List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();
        StringBuilder chuoiJson = new StringBuilder();
        chuoiJson.append("{\"danhsachsanpham\":[");

        for (int i=0 ; i<hoaDon.getListChiTiet().size();i++){

            chuoiJson.append("{");
            chuoiJson.append("\"masp\":"+hoaDon.getListChiTiet().get(i).getMaSp()+",");
            chuoiJson.append("\"soluong\":"+hoaDon.getListChiTiet().get(i).getSoLuong()+",");
            chuoiJson.append("\"giamgia\":"+hoaDon.getListChiTiet().get(i).getGiamGia());
            if (i==hoaDon.getListChiTiet().size()-1){
                chuoiJson.append("}");
            } else {
                chuoiJson.append("},");
            }

        }
        chuoiJson.append("]}");

        HashMap<String,String> hmMadg = new HashMap<>();
        hmMadg.put("danhsachsanpham",chuoiJson.toString());

        HashMap<String,String> hmMaSP = new HashMap<>();
        hmMaSP.put("tennguoinhan", hoaDon.getTenNguoiNhan());

        HashMap<String,String> hmTenThietBi = new HashMap<>();
        hmTenThietBi.put("sodt",hoaDon.getSoDT());

        HashMap<String,String> hmTieuDe = new HashMap<>();
        hmTieuDe.put("diachi",hoaDon.getDiaChi());

        HashMap<String,String> hmNoiDung = new HashMap<>();
        hmNoiDung.put("chuyenkhoan", String.valueOf(hoaDon.getChuyenKhoan()));

        HashMap<String,String> hmIDtaiKhoan = new HashMap<>();
        hmIDtaiKhoan.put("idtaikhoan", Data.code);

        attrs.add(hmGoiHam);
        attrs.add(hmMadg);
        attrs.add(hmMaSP);
        attrs.add(hmTenThietBi);
        attrs.add(hmTieuDe);
        attrs.add(hmNoiDung);
        attrs.add(hmIDtaiKhoan);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            String kq = jsonObject.getString("ketqua");
            if (kq.equals("true")){
                ketqua=true;
            }else{
                ketqua=false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ketqua;
    }
}
