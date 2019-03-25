package com.duong.tokyolife.Presenter.ChiTietSanPham;

import com.duong.tokyolife.Model.ChiTietSanPham.ChiTietSanPhamModel;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.View.ChiTietSanPham.IViewChiTietSanPham;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham{

    IViewChiTietSanPham iViewChiTietSanPham;
    ChiTietSanPhamModel chiTietSanPhamModel;

    public PresenterLogicChiTietSanPham(IViewChiTietSanPham iViewChiTietSanPham){
        this.iViewChiTietSanPham=iViewChiTietSanPham;
        chiTietSanPhamModel =  new ChiTietSanPhamModel();
    }

    @Override
    public void layChiTietSanPham(int masp) {
        SanPham sanPham = chiTietSanPhamModel.layChiTietSP(masp);
        if (sanPham.getMasp()>0){
            iViewChiTietSanPham.hienThiChiTietSanPham(sanPham);
            String[] hinhnho = sanPham.getAnhnho().split(",");
            iViewChiTietSanPham.hienThiSliderSanPham(hinhnho);
        }
    }
}
