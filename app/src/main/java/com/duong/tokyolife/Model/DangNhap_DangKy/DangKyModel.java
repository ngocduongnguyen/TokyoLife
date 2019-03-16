package com.duong.tokyolife.Model.DangNhap_DangKy;

import com.duong.tokyolife.Model.ObjectClass.NhanVien;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;
import com.duong.tokyolife.View.TrangChu.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DangKyModel {

    public boolean dangKyThanhVien(NhanVien nhanVien){
        boolean kiemTra=false;

        List<HashMap<String,String>> attribute = new ArrayList<>();

        HashMap<String,String> hmGoiham = new HashMap<>();
        hmGoiham.put("goiham","dangKyThanhVien");

        HashMap<String,String> hmTenNhanVien = new HashMap<>();
        hmTenNhanVien.put("tennv",nhanVien.getTennv());

        HashMap<String,String> hmTenDn = new HashMap<>();
        hmTenDn.put("tendn",nhanVien.getTendn());

        HashMap<String,String> hmMatKhau = new HashMap<>();
        hmMatKhau.put("matkhau",nhanVien.getMatkhau());

        attribute.add(hmGoiham);
        attribute.add(hmTenNhanVien);
        attribute.add(hmTenDn);
        attribute.add(hmMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attribute);
        downloadJSON.execute();

        try {
            String dulieuJson =downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String dulieu = jsonObject.getString("ketqua");
            if (dulieu.equals("true")){
                kiemTra=true;
            } else {
                kiemTra=false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kiemTra;
    }

}
