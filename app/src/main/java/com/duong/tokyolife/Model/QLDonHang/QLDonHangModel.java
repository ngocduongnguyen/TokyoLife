package com.duong.tokyolife.Model.QLDonHang;

import android.util.Log;

import com.duong.tokyolife.Model.ObjectClass.ChiTietDonHang;
import com.duong.tokyolife.Model.ObjectClass.DonHang;
import com.duong.tokyolife.Model.ObjectClass.HoaDon;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QLDonHangModel {
    public List<DonHang> layDanhSachDonHang(long idKhachHang){
        List<DonHang> ds = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> goiham = new HashMap<>();
        goiham.put("goiham","layHoaDon");
        HashMap<String,String> id = new HashMap<>();
        id.put("id", String.valueOf(idKhachHang));
        Collections.addAll(attrs,goiham,id);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("hoadon");
            int count = jsonArray.length();
            for (int i=0; i < count; i++){
                DonHang donHang = new DonHang();
                JSONObject hdJson = jsonArray.getJSONObject(i);
                donHang.setMaHD(hdJson.getInt("mahd"));
                donHang.setNgayMua(hdJson.getString("ngaymua"));
                donHang.setTrangthai(hdJson.getString("trangthai"));
                donHang.setTennguoinhan(hdJson.getString("tennguoinhan"));
                donHang.setSodt(hdJson.getString("sodt"));
                donHang.setDiachi(hdJson.getString("diachi"));

                List<ChiTietDonHang> lstChiTiet = new ArrayList<>();
                JSONArray chitietJSon = hdJson.getJSONArray("chitietdonhang");
                int countCt = chitietJSon.length();
                for (int j=0; j < countCt; j++){
                    ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
                    chiTietDonHang.setTensp(chitietJSon.getJSONObject(j).getString("tensp"));
                    chiTietDonHang.setSoluong(chitietJSon.getJSONObject(j).getInt("soluong"));
                    chiTietDonHang.setGiamgia(chitietJSon.getJSONObject(j).getInt("giamgia"));
                    chiTietDonHang.setGia(chitietJSon.getJSONObject(j).getInt("gia"));
                    lstChiTiet.add(chiTietDonHang);
                }
                donHang.setChiTietDonHang(lstChiTiet);
                ds.add(donHang);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public void huyDonHang(int madh){
        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> goiham = new HashMap<>();
        goiham.put("goiham","huyDonHang");
        HashMap<String,String> id = new HashMap<>();
        id.put("id", String.valueOf(madh));
        Collections.addAll(attrs,goiham,id);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();
        try {
            String data = downloadJSON.get();
            Log.d("kiemtra",data);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
