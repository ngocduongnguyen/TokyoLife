package com.duong.tokyolife.Model.SanPhamMoi;

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

public class SanPhamMoiModel {
    public List<SanPham> layDanhSachSpMoi(){
        List<SanPham> dsSP = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsGoiHam = new HashMap<>();
        hsGoiHam.put("goiham","layDanhSachSpMoi");
        attrs.add(hsGoiHam);
        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("dssanphammoi");
            int count = jsonArray.length();
            for (int i=0;i<count;i++){
                JSONObject spObject = jsonArray.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMasp(spObject.getInt("masp"));
                sanPham.setTensp(spObject.getString("tensp"));
                sanPham.setAnhlon(spObject.getString("hinhlon"));
                sanPham.setGia(spObject.getInt("gia"));
                sanPham.setGiamgia(spObject.getInt("giamgia"));
                dsSP.add(sanPham);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dsSP;
    }
}
