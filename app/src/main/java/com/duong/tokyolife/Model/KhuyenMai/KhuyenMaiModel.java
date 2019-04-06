package com.duong.tokyolife.Model.KhuyenMai;

import com.duong.tokyolife.Model.ObjectClass.ChiTietKhuyenMai;
import com.duong.tokyolife.Model.ObjectClass.KhuyenMai;
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

public class KhuyenMaiModel {
    public List<KhuyenMai> layDsKhuyenMai(){
        List<KhuyenMai> ds = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hmGoiHam = new HashMap<>();
        hmGoiHam.put("goiham","layChiTietKhuyenMai");

        attrs.add(hmGoiHam);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("chitietkhuyenmai");
            int count = jsonArray.length();
            for (int i=0; i<count; i++){

                JSONObject json = jsonArray.getJSONObject(i);

                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMaKm(json.getInt("makm"));
                khuyenMai.setTenKm(json.getString("tenkm"));
                khuyenMai.setHinhKhuyenMai(json.getString("hinhkhuyenmai"));

                JSONArray jsonChiTiet = json.getJSONArray("dschitiet");
                int countct = jsonChiTiet.length();
                List<SanPham> dsSPKM = new ArrayList<>();
                for (int j=0;j<countct;j++){
                    JSONObject jsonCt = jsonChiTiet.getJSONObject(j);
                    SanPham sanPham = new SanPham();
                    sanPham.setMasp(jsonCt.getInt("masp"));
                    sanPham.setTensp(jsonCt.getString("tensp"));
                    sanPham.setAnhlon(ServerName.SERVER_IMG+jsonCt.getString("hinhlon"));
                    sanPham.setAnhnho(jsonCt.getString("hinhnho"));
                    sanPham.setGia(jsonCt.getInt("gia"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPhanTramKhuyenMai(jsonCt.getInt("phantramkm"));
                    chiTietKhuyenMai.setMaKm(jsonCt.getInt("makm"));
                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                    dsSPKM.add(sanPham);
                }
                khuyenMai.setDsSanPhamKhuyenMai(dsSPKM);
                ds.add(khuyenMai);
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
}
