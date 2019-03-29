package com.duong.tokyolife.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.duong.tokyolife.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class GioHangModel {
    SQLiteDatabase database;
    public void MoKetNoi(Context context){

        DataSanPham dataSanPham = new DataSanPham(context);
        database = dataSanPham.getWritableDatabase();

    }

    public boolean ThemGioHang(SanPham sanPham){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_Gio_Hang_Masp,sanPham.getMasp());
        contentValues.put(DataSanPham.TB_Gio_Hang_Tensp,sanPham.getTensp());
        contentValues.put(DataSanPham.TB_Gio_Hang_Giatien,sanPham.getGia());
        contentValues.put(DataSanPham.TB_Gio_Hang_Hinhanh,sanPham.getHinhGioHang());

        long id = database.insert(DataSanPham.TB_Gio_Hang,null,contentValues);
        if (id>0)
        {
            return true;
        } else {
            return false;
        }
    }


    public List<SanPham> layDSsPTrongGioHang(){
        List<SanPham> ds = new ArrayList<>();

        String truyvan = "select * from "+DataSanPham.TB_Gio_Hang;

        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Masp));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Tensp));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Giatien));
            byte[] hinhhanh = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Hinhanh));

            SanPham sanPham = new SanPham();
            sanPham.setMasp(masp);
            sanPham.setTensp(tensp);
            sanPham.setGia(giatien);
            sanPham.setHinhGioHang(hinhhanh);

            ds.add(sanPham);
        }
        return ds;
    }
}
