package com.duong.tokyolife.Presenter.HienThiSPTheoThuongHieu;

import com.duong.tokyolife.Model.HienThiSPTheoThuongHieu.HienThiSPTheoThuongHieuModel;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.View.HienThiSPTheoThuongHieu.IViewHienThiSPTheoThuongHieu;

import java.util.List;

public class PresenterLogicHienThiSPTheoThuongHieu implements IPresenterHienThiSPTheoThuongHieu {

    IViewHienThiSPTheoThuongHieu viewHienThiSPTheoThuongHieu;
    HienThiSPTheoThuongHieuModel hienThiSPTheoThuongHieuModel;

    public PresenterLogicHienThiSPTheoThuongHieu(IViewHienThiSPTheoThuongHieu viewHienThiSPTheoThuongHieu){
        this.viewHienThiSPTheoThuongHieu=viewHienThiSPTheoThuongHieu;
        hienThiSPTheoThuongHieuModel=new HienThiSPTheoThuongHieuModel();
    }

    @Override
    public void layDSspTheoThuogHieu(int math) {
        List<SanPham> dsSanPham = hienThiSPTheoThuongHieuModel.layDanhSachSPtheoTH(math);
        if (dsSanPham.size()>0){
            viewHienThiSPTheoThuongHieu.hienThiDSSanPham(dsSanPham);
        } else{
            viewHienThiSPTheoThuongHieu.loiHienThiDSSanPham();
        }
    }
}
