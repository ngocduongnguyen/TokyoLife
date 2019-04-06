package com.duong.tokyolife.Presenter.GioHang;

import android.content.Context;

import com.duong.tokyolife.Model.GioHang.GioHangModel;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.View.GioHang.IViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements IPresenterGioHang {
    IViewGioHang iViewGioHang;
    GioHangModel gioHangModel;
    public PresenterLogicGioHang(IViewGioHang iViewGioHang){
        this.iViewGioHang=iViewGioHang;
        gioHangModel=new GioHangModel();
    }

    Context context;
    @Override
    public void layDanhSachGioHang(Context context) {
        List<SanPham> ds = gioHangModel.layDSsPTrongGioHang(context);
        if (ds.size()>0){
            iViewGioHang.hienThiDsSanPhamTrongGioHang(ds);
        }
    }
}
