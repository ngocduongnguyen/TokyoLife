package com.duong.tokyolife.Model.TimKiem;

import com.duong.tokyolife.Model.ObjectClass.ChiTietKhuyenMai;
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

public class TimKiemModel {
    public List<SanPham> layDanhSachSPtheoLoaiSP(String maloaisp){
        List<SanPham> dsSP = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsGoiHam = new HashMap<>();
        hsGoiHam.put("goiham","timKiemSanpHamTheoTenSP");
        HashMap<String,String> hsMath = new HashMap<>();
        hsMath.put("tensp",String.valueOf(maloaisp));
        attrs.add(hsGoiHam);
        attrs.add(hsMath);
        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("dstimkiem");
            int count = jsonArray.length();
            for (int i=0;i<count;i++){
                JSONObject spObject = jsonArray.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMasp(spObject.getInt("masp"));
                sanPham.setTensp(spObject.getString("tensp"));
                sanPham.setAnhlon(ServerName.SERVER_IMG+spObject.getString("hinhlon"));
                sanPham.setGia(spObject.getInt("gia"));

                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPhanTramKhuyenMai(spObject.getInt("phantramkm"));
                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

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
