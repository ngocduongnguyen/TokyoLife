package com.duong.tokyolife.Model.ChiTietSanPham;

import android.widget.ListView;

import com.duong.tokyolife.Model.ObjectClass.ChiTietSanPham;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChiTietSanPhamModel {
    public SanPham layChiTietSP(int masp){
        SanPham sanPham = new SanPham();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hmGoiHam = new HashMap<>();
        hmGoiHam.put("goiham","layChiTietSanPham");

        HashMap<String,String> hmMaSP = new HashMap<>();
        hmMaSP.put("masp", String.valueOf(masp));

        attrs.add(hmGoiHam);
        attrs.add(hmMaSP);
        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("chitiet");
            int count = jsonArray.length();
            for (int i=0;i<count;i++){
                JSONObject spJson = jsonArray.getJSONObject(i);
                sanPham.setMasp(spJson.getInt("masp"));
                sanPham.setTensp(spJson.getString("tensp"));
                sanPham.setGia(spJson.getInt("gia"));
                sanPham.setAnhlon(spJson.getString("hinhlon"));
                sanPham.setAnhnho(spJson.getString("hinhnho"));
                sanPham.setThongtin(spJson.getString("thongtin"));
                sanPham.setSoluong(spJson.getInt("soluong"));
                sanPham.setMaloaisp(spJson.getInt("maloaisp"));
                sanPham.setMath(spJson.getInt("math"));
                sanPham.setLuotmua(spJson.getInt("luotmua"));
                //lay danh sach thong tin
                List<ChiTietSanPham> ds = new ArrayList<>();
                JSONArray dsThongTin = spJson.getJSONArray("dsthongtin");
                int countTT = dsThongTin.length();
                for (int j=0;j<countTT;j++){
                    JSONObject ttJson = dsThongTin.getJSONObject(j);
                    ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                    chiTietSanPham.setChiTietSP(ttJson.getString("chitietsp"));
                    chiTietSanPham.setGiaTri(ttJson.getString("giatri"));
                    ds.add(chiTietSanPham);
                }
                sanPham.setDsChiTietSP(ds);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPham;
    }
}
