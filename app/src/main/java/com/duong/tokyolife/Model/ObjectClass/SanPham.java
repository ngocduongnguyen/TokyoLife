package com.duong.tokyolife.Model.ObjectClass;

import java.util.List;

public class SanPham {
    private int masp,gia,soluong,maloaisp,math,luotmua;
    private String tensp,anhlon,anhnho,thongtin;
    private List<ChiTietSanPham> dsChiTietSP;
    private byte[] hinhGioHang;

    public byte[] getHinhGioHang() {
        return hinhGioHang;
    }

    public void setHinhGioHang(byte[] hinhGioHang) {
        this.hinhGioHang = hinhGioHang;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(int maloaisp) {
        this.maloaisp = maloaisp;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getLuotmua() {
        return luotmua;
    }

    public void setLuotmua(int luotmua) {
        this.luotmua = luotmua;
    }

    public String getAnhlon() {
        return anhlon;
    }

    public void setAnhlon(String anhlon) {
        this.anhlon = anhlon;
    }

    public String getAnhnho() {
        return anhnho;
    }

    public void setAnhnho(String anhnho) {
        this.anhnho = anhnho;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public List<ChiTietSanPham> getDsChiTietSP() {
        return dsChiTietSP;
    }

    public void setDsChiTietSP(List<ChiTietSanPham> dsChiTietSP) {
        this.dsChiTietSP = dsChiTietSP;
    }
}
