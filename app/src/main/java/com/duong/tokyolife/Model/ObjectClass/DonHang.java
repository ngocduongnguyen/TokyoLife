package com.duong.tokyolife.Model.ObjectClass;

import java.util.List;

public class DonHang {
    private int maHD;
    private String ngayMua;
    private String trangthai;
    private String tennguoinhan;
    private String sodt;
    private String diachi;
    private List<ChiTietDonHang> chiTietDonHang;

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTennguoinhan() {
        return tennguoinhan;
    }

    public void setTennguoinhan(String tennguoinhan) {
        this.tennguoinhan = tennguoinhan;
    }

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public List<ChiTietDonHang> getChiTietDonHang() {
        return chiTietDonHang;
    }

    public void setChiTietDonHang(List<ChiTietDonHang> chiTietDonHang) {
        this.chiTietDonHang = chiTietDonHang;
    }
}
