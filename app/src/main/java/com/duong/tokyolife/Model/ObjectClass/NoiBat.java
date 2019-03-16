package com.duong.tokyolife.Model.ObjectClass;

import java.util.List;

public class NoiBat {
    List<ThuongHieu> listTH;
    List<SanPham> listSP;
//    String hinhSP;

//    public String getHinhSP() {
//        return hinhSP;
//    }
//
//    public void setHinhSP(String hinhSP) {
//        this.hinhSP = hinhSP;
//    }

    public List<ThuongHieu> getListTH() {
        return listTH;
    }

    public void setListTH(List<ThuongHieu> listTH) {
        this.listTH = listTH;
    }

    public List<SanPham> getListSP() {
        return listSP;
    }

    public void setListSP(List<SanPham> listSP) {
        this.listSP = listSP;
    }
}
