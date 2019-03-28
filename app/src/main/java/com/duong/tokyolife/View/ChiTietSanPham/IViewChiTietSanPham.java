package com.duong.tokyolife.View.ChiTietSanPham;

import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.Model.ObjectClass.SanPham;

import java.util.List;

public interface IViewChiTietSanPham {
    void hienThiChiTietSanPham(SanPham sanPham);
    void hienThiSliderSanPham(String[] linkHinhAnh);
    void hienThiDanhGia(List<DanhGia> ds);
    void layTBSoSao(float count);
}
