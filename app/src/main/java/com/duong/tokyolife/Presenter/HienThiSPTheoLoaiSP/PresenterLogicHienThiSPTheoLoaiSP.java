package com.duong.tokyolife.Presenter.HienThiSPTheoLoaiSP;

import android.content.Context;

import com.duong.tokyolife.Model.HienThiSPTheoLoaiSP.HienThiSPTheoLoaiSPModel;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.View.HienThiSPTheoLoaiSP.IViewHienThiSPTheoLoaiSP;

import java.util.List;

public class PresenterLogicHienThiSPTheoLoaiSP implements IPresenterHienThiSPTheoLoaiSP{

    IViewHienThiSPTheoLoaiSP iViewHienThiSPTheoLoaiSP;
    HienThiSPTheoLoaiSPModel hienThiSPTheoLoaiSPModel;

    public PresenterLogicHienThiSPTheoLoaiSP(IViewHienThiSPTheoLoaiSP iViewHienThiSPTheoLoaiSP){
        this.iViewHienThiSPTheoLoaiSP=iViewHienThiSPTheoLoaiSP;
        hienThiSPTheoLoaiSPModel = new HienThiSPTheoLoaiSPModel();
    }

    @Override
    public void layDanhSachSanPhamTheoMaLoai(int maloaisp) {
        List<SanPham> dsSanPham = hienThiSPTheoLoaiSPModel.layDanhSachSPtheoLoaiSP(maloaisp);
        if (dsSanPham.size()>0){
            iViewHienThiSPTheoLoaiSP.hienThiSanPhamTheoLoaiSP(dsSanPham);
        } else{
            iViewHienThiSPTheoLoaiSP.hienThiLoi();
        }
    }
}
