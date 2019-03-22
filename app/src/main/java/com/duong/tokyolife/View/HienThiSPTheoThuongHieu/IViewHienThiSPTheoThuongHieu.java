package com.duong.tokyolife.View.HienThiSPTheoThuongHieu;

import com.duong.tokyolife.Model.ObjectClass.SanPham;

import java.util.List;

public interface IViewHienThiSPTheoThuongHieu {
    void hienThiDSSanPham(List<SanPham> sanPhamList);
    void loiHienThiDSSanPham();
}