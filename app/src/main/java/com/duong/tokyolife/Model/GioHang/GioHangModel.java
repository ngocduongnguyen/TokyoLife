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
        contentValues.put(DataSanPham.TB_Gio_Hang_Soluong,sanPham.getSoluong());
        contentValues.put(DataSanPham.TB_Gio_Hang_SoluongTonkho,sanPham.getSoluongtonkho());
        contentValues.put(DataSanPham.TB_Gio_Hang_GiamGia,sanPham.getGiamgia());

        long id = database.insert(DataSanPham.TB_Gio_Hang,null,contentValues);
        if (id>0)
        {
            return true;
        } else {
            return false;
        }
    }


    public List<SanPham> layDSsPTrongGioHang(Context context){
        MoKetNoi(context);

        List<SanPham> ds = new ArrayList<>();

        String truyvan = "select * from "+DataSanPham.TB_Gio_Hang;

        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Masp));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Tensp));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Giatien));
            byte[] hinhhanh = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Hinhanh));
            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_Soluong));
            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_SoluongTonkho));
            int giamgia = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_Gio_Hang_GiamGia));

            SanPham sanPham = new SanPham();
            sanPham.setMasp(masp);
            sanPham.setTensp(tensp);
            sanPham.setGia(giatien);
            sanPham.setHinhGioHang(hinhhanh);
            sanPham.setSoluong(soluong);
            sanPham.setSoluongtonkho(soluongtonkho);
            sanPham.setGiamgia(giamgia);
            ds.add(sanPham);

            cursor.moveToNext();
        }
        return ds;
    }

    public boolean xoaSP(int masp){
        int kiemtra = database.delete(DataSanPham.TB_Gio_Hang,DataSanPham.TB_Gio_Hang_Masp+"="+masp,null);
        if (kiemtra>0){
            return true;
        } else {
            return false;
        }
    }

    public boolean capNhapGioHang(Context context, int masp,int soluong){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_Gio_Hang_Soluong,soluong);
        MoKetNoi(context);
        int id = database.update(DataSanPham.TB_Gio_Hang,contentValues,DataSanPham.TB_Gio_Hang_Masp+" = "+masp,null);
        if (id>0){
            return true;
        } else {
            return false;
        }
    }
}
