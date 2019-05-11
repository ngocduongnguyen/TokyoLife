package com.duong.tokyolife.Model.TinTuc;

import com.duong.tokyolife.Model.ObjectClass.BaiViet;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TinTucModel {
    public List<BaiViet> layDanhSachBaiViet(){
        List<BaiViet> lstBaiViet = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> goiHam = new HashMap<>();
        goiHam.put("goiham","layDanhSachBaiViet");
        attrs.add(goiHam);
        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("dsbaiviet");
            int count = jsonArray.length();
            for (int i = 0; i < count; i++){
                JSONObject bvJSON = jsonArray.getJSONObject(i);

                BaiViet baiViet = new BaiViet();
                baiViet.setMatintuc(bvJSON.getInt("matintuc"));
                baiViet.setTieuDe(bvJSON.getString("title"));
                baiViet.setAnhDaiDien(ServerName.SERVER_IMG+bvJSON.getString("bg"));
                baiViet.setMoTa(bvJSON.getString("description"));
                lstBaiViet.add(baiViet);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lstBaiViet;
    }


    public BaiViet layChiTietBaiViet(int matin){
        BaiViet baiViet = new BaiViet();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> goiHam = new HashMap<>();
        goiHam.put("goiham","layChiTietBaiViet");
        attrs.add(goiHam);

        HashMap<String,String> matintuc = new HashMap<>();
        matintuc.put("matintuc",String.valueOf(matin));
        attrs.add(matintuc);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("chitietbaiviet");

            JSONObject bvJSON = jsonArray.getJSONObject(0);


            baiViet.setMatintuc(bvJSON.getInt("matintuc"));
            baiViet.setTieuDe(bvJSON.getString("title"));
            baiViet.setAnhDaiDien(ServerName.SERVER_IMG+bvJSON.getString("bg"));
            baiViet.setMoTa(bvJSON.getString("description"));
            baiViet.setNoiDung(bvJSON.getString("content"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return baiViet;
    }
}
