package com.duong.tokyolife.Model.TrangChu;

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

public class NoiBatModel {
    public List<SanPham> laySanPhamNoiBat(){

        List<SanPham> listSpNoiBat = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hmGoiham = new HashMap<>();
        hmGoiham.put("goiham","layDanhSachSanPhamNoiBat");
        attrs.add(hmGoiham);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("sanphamnoibat");
            int count = jsonArray.length();
            for (int i=0;i<count;i++){



            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listSpNoiBat;
    }
}