package com.duong.tokyolife.Model.TrangChu;

import com.duong.tokyolife.Model.ObjectClass.LoaiSanPham;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DataJSONMenuLeft {
    List<LoaiSanPham> sanPhamList;
    public List<LoaiSanPham> parseJsonLoaiSanPham(String dataJson){
        sanPhamList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("loaisanpham");
            int count = jsonArray.length();
            for (int i = 0; i< count; i++){
                JSONObject object = jsonArray.getJSONObject(i);

                LoaiSanPham loaiSanPham = new LoaiSanPham();
                loaiSanPham.setMaLoaiSP(Integer.parseInt(object.getString("maloaisp")));
                loaiSanPham.setTenLoaiSP(object.getString("tenloaisp"));
                sanPhamList.add(loaiSanPham);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }

    public List<LoaiSanPham> layDanhSachLoaiSP() {

        sanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attr = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        map.put("goiham","layDanhSachLoaiSP");
        attr.add(map);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attr);
        downloadJSON.execute();

        try {
            String dataJSon = downloadJSON.get();
            sanPhamList = parseJsonLoaiSanPham(dataJSon);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }
}
