package com.duong.tokyolife.Presenter.DanhSachDanhGia;

import com.duong.tokyolife.Model.ChiTietSanPham.ChiTietSanPhamModel;
import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.View.DanhGia.IViewDanhSachDanhGia;

import java.util.List;

public class PresenterDSDanhGia implements IPresenterDSDanhGia {
    IViewDanhSachDanhGia iViewDanhSachDanhGia;
    ChiTietSanPhamModel chiTietSanPhamModel;
    public PresenterDSDanhGia(IViewDanhSachDanhGia iViewDanhSachDanhGia){
        this.iViewDanhSachDanhGia=iViewDanhSachDanhGia;
        chiTietSanPhamModel = new ChiTietSanPhamModel();
    }
    @Override
    public void laydsDanhGiaTheoSP(int masp, int limit) {
        List<DanhGia> ds = chiTietSanPhamModel.layDanhSachDanhGia(masp,limit);
        if (ds.size()>0){
            iViewDanhSachDanhGia.hienThiDanhSachDanhGia(ds);
        }
    }
}
