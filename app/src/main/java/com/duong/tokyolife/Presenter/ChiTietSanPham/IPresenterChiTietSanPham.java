package com.duong.tokyolife.Presenter.ChiTietSanPham;

import android.content.Context;

import com.duong.tokyolife.Model.ObjectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void layChiTietSanPham(int masp);
    void laydsDanhGiaTheoSP(int masp,int limit);
    void layTBDanhGiaSP(int masp);
    void themGioHang(SanPham sanPham, Context context);
}
