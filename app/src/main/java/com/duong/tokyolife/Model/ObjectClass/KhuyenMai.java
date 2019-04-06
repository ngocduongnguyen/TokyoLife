package com.duong.tokyolife.Model.ObjectClass;

import java.util.List;

public class KhuyenMai {
    int maKm,maLoaiSp;
    String tenKm,ngayBatDau,ngayKetThuc,hinhKhuyenMai;
    List<SanPham> dsSanPhamKhuyenMai;

    public int getMaKm() {
        return maKm;
    }

    public void setMaKm(int maKm) {
        this.maKm = maKm;
    }

    public int getMaLoaiSp() {
        return maLoaiSp;
    }

    public void setMaLoaiSp(int maLoaiSp) {
        this.maLoaiSp = maLoaiSp;
    }

    public String getTenKm() {
        return tenKm;
    }

    public void setTenKm(String tenKm) {
        this.tenKm = tenKm;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getHinhKhuyenMai() {
        return hinhKhuyenMai;
    }

    public void setHinhKhuyenMai(String hinhKhuyenMai) {
        this.hinhKhuyenMai = hinhKhuyenMai;
    }

    public List<SanPham> getDsSanPhamKhuyenMai() {
        return dsSanPhamKhuyenMai;
    }

    public void setDsSanPhamKhuyenMai(List<SanPham> dsSanPhamKhuyenMai) {
        this.dsSanPhamKhuyenMai = dsSanPhamKhuyenMai;
    }
}
