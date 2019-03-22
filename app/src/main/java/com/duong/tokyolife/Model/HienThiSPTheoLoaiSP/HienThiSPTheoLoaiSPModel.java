package com.duong.tokyolife.Model.HienThiSPTheoLoaiSP;

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

public class HienThiSPTheoLoaiSPModel {

    public List<SanPham> layDanhSachSPtheoLoaiSP(int maloaisp){
        List<SanPham> dsSP = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsGoiHam = new HashMap<>();
        hsGoiHam.put("goiham","layDanhSachSanPhamTheoMaLoai");
        HashMap<String,String> hsMath = new HashMap<>();
        hsMath.put("maloaisp",String.valueOf(maloaisp));
        attrs.add(hsGoiHam);
        attrs.add(hsMath);
        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("sanphambymaloaisp");
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
