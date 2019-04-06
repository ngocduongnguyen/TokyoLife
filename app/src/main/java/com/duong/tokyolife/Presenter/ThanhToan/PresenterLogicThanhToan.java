package com.duong.tokyolife.Presenter.ThanhToan;

import android.content.Context;

import com.duong.tokyolife.Model.GioHang.GioHangModel;
import com.duong.tokyolife.Model.ObjectClass.HoaDon;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Model.ThanhToan.ThanhToanModel;
import com.duong.tokyolife.View.ThanhToan.IViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements IPresenterThanhToan {
    IViewThanhToan iViewThanhToan;
    ThanhToanModel thanhToanModel;
    GioHangModel gioHangModel;
    Context context;
    List<SanPham> ds;

    public PresenterLogicThanhToan(IViewThanhToan iViewThanhToan, Context context){
        this.iViewThanhToan=iViewThanhToan;
        this.context=context;
        thanhToanModel = new ThanhToanModel();
        gioHangModel = new GioHangModel();
    }

    @Override
    public void themHoaDon(HoaDon hoaDon) {
        boolean kiemtra = thanhToanModel.themHoaDon(hoaDon);
        if (kiemtra){
            iViewThanhToan.datHangThanhCong();
            gioHangModel.MoKetNoi(context);
            for (SanPham a : ds){
                gioHangModel.xoaSP(a.getMasp());
            }
        } else {
            iViewThanhToan.datHangThatBai();
        }
    }

    @Override
    public void layDanhSachSanPhamtrongGioHang() {
        ds = gioHangModel.layDSsPTrongGioHang(context);
        if (ds.size()>0){
            iViewThanhToan.layDanhSachSanPhamTrongGioHang(ds);
        }
    }
}
