package com.duong.tokyolife.Model.TrangChu;

import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Model.ObjectClass.ThuongHieu;
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
                JSONObject spObject = jsonArray.getJSONObject(i);

                SanPham sanPham = new SanPham();
                sanPham.setMasp(spObject.getInt("masp"));
                sanPham.setTensp(spObject.getString("tensp"));
                sanPham.setAnhlon(spObject.getString("hinhlon"));
//                sanPham.setAnhnho(spObject.getString("anhnho"));
//                sanPham.setThongtin(spObject.getString("thongtin"));
                sanPham.setGia(spObject.getInt("gia"));
//                sanPham.setSoluong(spObject.getInt("soluong"));
//                sanPham.setMaloaisp(spObject.getInt("maloaisp"));
//                sanPham.setMath(spObject.getInt("math"));
//                sanPham.setLuotmua(spObject.getInt("luotmua"));

                listSpNoiBat.add(sanPham);
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

    public List<ThuongHieu> layDsThuongHieu(){
        List<ThuongHieu> dsThuognHieu = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hmGoiham = new HashMap<>();
        hmGoiham.put("goiham","layDanhSachThuongHieu");
        attrs.add(hmGoiham);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJSon = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSon);
            JSONArray jsonArray = jsonObject.getJSONArray("thuonghieu");

            int count = jsonArray.length();
            for (int i=0;i<count;i++){

                JSONObject thuongHieuJSon = jsonArray.getJSONObject(i);

                ThuongHieu thuongHieu = new ThuongHieu();
                thuongHieu.setMath(thuongHieuJSon.getInt("math"));
                thuongHieu.setTenth(thuongHieuJSon.getString("tenth"));
                thuongHieu.setHinhth(thuongHieuJSon.getString("hinhth"));

                dsThuognHieu.add(thuongHieu);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dsThuognHieu;
    }
}