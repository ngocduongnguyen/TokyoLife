package com.duong.tokyolife.Model.ObjectClass;

import java.util.List;

public class HoaDon {
    int mahd;
    String ngayMua,ngayGiao,trangThai,tenNguoiNhan,soDT,diaChi,maChuyenKhoan;
    int chuyenKhoan;
    List<ChiTietHoaDon> listChiTiet;

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaChuyenKhoan() {
        return maChuyenKhoan;
    }

    public void setMaChuyenKhoan(String maChuyenKhoan) {
        this.maChuyenKhoan = maChuyenKhoan;
    }

    public int getChuyenKhoan() {
        return chuyenKhoan;
    }

    public void setChuyenKhoan(int chuyenKhoan) {
        this.chuyenKhoan = chuyenKhoan;
    }

    public List<ChiTietHoaDon> getListChiTiet() {
        return listChiTiet;
    }

    public void setListChiTiet(List<ChiTietHoaDon> listChiTiet) {
        this.listChiTiet = listChiTiet;
    }
}
