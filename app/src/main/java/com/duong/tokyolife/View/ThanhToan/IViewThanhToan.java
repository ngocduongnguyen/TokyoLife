package com.duong.tokyolife.View.ThanhToan;

import com.duong.tokyolife.Model.ObjectClass.HoaDon;
import com.duong.tokyolife.Model.ObjectClass.SanPham;

import java.util.List;

public interface IViewThanhToan {
    void datHangThanhCong();
    void datHangThatBai();
    void layDanhSachSanPhamTrongGioHang(List<SanPham> dsSanPham);
}
