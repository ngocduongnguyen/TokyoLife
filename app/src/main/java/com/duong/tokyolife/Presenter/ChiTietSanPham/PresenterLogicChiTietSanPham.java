package com.duong.tokyolife.Presenter.ChiTietSanPham;

import com.duong.tokyolife.Model.ChiTietSanPham.ChiTietSanPhamModel;
import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.View.ChiTietSanPham.IViewChiTietSanPham;

import java.text.DecimalFormat;
import java.util.List;

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

    @Override
    public void laydsDanhGiaTheoSP(int masp, int limit) {
        List<DanhGia> ds = chiTietSanPhamModel.layDanhSachDanhGia(masp,limit);
        if (ds.size()>0){
            iViewChiTietSanPham.hienThiDanhGia(ds);
        }
    }

    @Override
    public void layTBDanhGiaSP(int masp) {
        List<DanhGia> ds = chiTietSanPhamModel.layDanhSachDanhGia(masp,0);
        double tb = 0.0;
        double tongdg = ds.size();
        double sodanhgia = 0.0;
        for (DanhGia danhGia: ds){
            sodanhgia=sodanhgia+danhGia.getSosao();
        }
        if (tongdg==0){
            tb=0.0;
        } else {
            tb = sodanhgia/tongdg;
        }
        iViewChiTietSanPham.layTBSoSao((float) tb);
    }
}
