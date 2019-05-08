package com.duong.tokyolife.Model.GioHang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataSanPham extends SQLiteOpenHelper {

    public static String TB_Gio_Hang="giohang";
    public static String TB_Gio_Hang_Masp="masp";
    public static String TB_Gio_Hang_Tensp="tensp";
    public static String TB_Gio_Hang_Giatien="giatien";
    public static String TB_Gio_Hang_Hinhanh="hinhanh";
    public static String TB_Gio_Hang_Soluong="soluong";
    public static String TB_Gio_Hang_SoluongTonkho="soluongtonkho";
    public static String TB_Gio_Hang_GiamGia="giamgia";

    public static String TB_Yeu_Thich="yeuthich";
    public static String TB_Yeu_Thich_Masp="masp";
    public static String TB_Yeu_Thich_Tensp="tensp";
    public static String TB_Yeu_Thich_Giatien="giatien";
    public static String TB_Yeu_Thich_Hinhanh="hinhanh";

    public DataSanPham(Context context) {
        super(context, "SqlGioHang", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tbGioHang = "create table "+TB_Gio_Hang+" ("+TB_Gio_Hang_Masp+" integer primary key," +
                ""+TB_Gio_Hang_Tensp+" text, "+TB_Gio_Hang_Giatien+" real, "+TB_Gio_Hang_Hinhanh+" blob, "
                +TB_Gio_Hang_Soluong+" integer, "+TB_Gio_Hang_SoluongTonkho+" integer, "+TB_Gio_Hang_GiamGia+" integer)";

//        String tbYeuThich = "create table "+TB_Yeu_Thich+" ("+TB_Yeu_Thich_Masp+" integer primary key," +
//                ""+TB_Yeu_Thich_Tensp+" text, "+TB_Yeu_Thich_Giatien+" real, "+TB_Yeu_Thich_Hinhanh+" blob)";

        db.execSQL(tbGioHang);
//        db.execSQL(tbYeuThich);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
