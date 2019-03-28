package com.duong.tokyolife.Model.DanhGia;

import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.Utils.DownloadJSON;
import com.duong.tokyolife.Utils.ServerName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DanhGiaModel {
    public boolean themDanhGia(DanhGia danhGia){
        boolean ketqua = false;

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hmGoiHam = new HashMap<>();
        hmGoiHam.put("goiham","themDanhGia");

        HashMap<String,String> hmMadg = new HashMap<>();
        hmMadg.put("madg",danhGia.getMadg());

        HashMap<String,String> hmMaSP = new HashMap<>();
        hmMaSP.put("masp", String.valueOf(danhGia.getMasp()));

        HashMap<String,String> hmTenThietBi = new HashMap<>();
        hmTenThietBi.put("tenthietbi",danhGia.getTenthietbi());

        HashMap<String,String> hmTieuDe = new HashMap<>();
        hmTieuDe.put("tieude",danhGia.getTieude());

        HashMap<String,String> hmNoiDung = new HashMap<>();
        hmNoiDung.put("noidung",danhGia.getNoidung());

        HashMap<String,String> hmSoSao = new HashMap<>();
        hmSoSao.put("sosao", String.valueOf(danhGia.getSosao()));

        attrs.add(hmGoiHam);
        attrs.add(hmMadg);
        attrs.add(hmMaSP);
        attrs.add(hmTenThietBi);
        attrs.add(hmTieuDe);
        attrs.add(hmNoiDung);
        attrs.add(hmSoSao);

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
}
