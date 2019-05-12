package com.duong.tokyolife.Model.TaiKhoan;

import com.duong.tokyolife.Model.ObjectClass.NhanVien;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TaiKhoanModel {

    public NhanVien layThongTinTaiKhoan(long idkh){
        NhanVien nhanVien = new NhanVien();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> goiham = new HashMap<>();
        goiham.put("goiham","thongtintaikhoan");
        HashMap<String,String> idkhachhang = new HashMap<>();
        idkhachhang.put("idkhachhang", String.valueOf(idkh));
        Collections.addAll(attrs,goiham,idkhachhang);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();

        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("thongtintaikhoan");
            int count = jsonArray.length();
            for (int i = 0; i<count; i++){
                nhanVien.setManv(jsonArray.getJSONObject(i).getLong("manv"));
                nhanVien.setTennv(jsonArray.getJSONObject(i).getString("tennv"));
                nhanVien.setTendn(jsonArray.getJSONObject(i).getString("tendn"));
                nhanVien.setMatkhau(jsonArray.getJSONObject(i).getString("matkhau"));
                nhanVien.setDiachi(jsonArray.getJSONObject(i).getString("diachi"));
                nhanVien.setNgaysinh(jsonArray.getJSONObject(i).getString("ngaysinh"));
                nhanVien.setSodt(jsonArray.getJSONObject(i).getString("sodt"));

                if (jsonArray.getJSONObject(i).getString("gioitinh")!="null")
                    nhanVien.setGioitinh(jsonArray.getJSONObject(i).getInt("gioitinh"));
                else
                    nhanVien.setGioitinh(-1);

                nhanVien.setCmnd(jsonArray.getJSONObject(i).getString("cmnd"));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nhanVien;
    }

    public boolean updatetaiKhoan(NhanVien nhanVien){
        boolean ketqua = false;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> goiham = new HashMap<>();
        goiham.put("goiham","updateTaiKhoan");

        HashMap<String,String> manv = new HashMap<>();
        manv.put("manv", String.valueOf(nhanVien.getManv()));

        HashMap<String,String> tennv = new HashMap<>();
        tennv.put("tennv",nhanVien.getTennv());

        HashMap<String,String> tendn = new HashMap<>();
        tendn.put("tendn",nhanVien.getTendn());

        HashMap<String,String> matkhau = new HashMap<>();
        matkhau.put("matkhau",nhanVien.getMatkhau());

        HashMap<String,String> ngaysinh = new HashMap<>();
        ngaysinh.put("ngaysinh",nhanVien.getNgaysinh());

        HashMap<String,String> diachi = new HashMap<>();
        diachi.put("diachi",nhanVien.getDiachi());

        HashMap<String,String> sodt = new HashMap<>();
        sodt.put("sodt",nhanVien.getSodt());

        HashMap<String,String> gioitinh = new HashMap<>();
        gioitinh.put("gioitinh", String.valueOf(nhanVien.getGioitinh()));

        HashMap<String,String> cmnd = new HashMap<>();
        cmnd.put("cmnd",nhanVien.getCmnd());

        Collections.addAll(attrs,goiham,manv,tennv,tendn,matkhau,ngaysinh,diachi,sodt,gioitinh,cmnd);

        DownloadJSON downloadJSON = new DownloadJSON(ServerName.SERVER_NAME,attrs);
        downloadJSON.execute();
        try {
            String dataJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJson);
            String kq = jsonObject.getString("ketqua");
            if (kq.equals("true")){
                ketqua=true;
            }else{
                ketqua=false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public void xoaTaiKhoan(){

    }
}
