package com.duong.tokyolife.Model.ObjectClass;

import java.util.List;

public class NoiBat {
    List<SanPham> listLoaiSpNoiBat;
    List<ThuongHieu> listThuongHieuNoiBat;
    boolean chonTable;

    public List<SanPham> getListLoaiSpNoiBat() {
        return listLoaiSpNoiBat;
    }

    public void setListLoaiSpNoiBat(List<SanPham> listLoaiSpNoiBat) {
        this.listLoaiSpNoiBat = listLoaiSpNoiBat;
    }

    public List<ThuongHieu> getListThuongHieuNoiBat() {
        return listThuongHieuNoiBat;
    }

    public void setListThuongHieuNoiBat(List<ThuongHieu> listThuongHieuNoiBat) {
        this.listThuongHieuNoiBat = listThuongHieuNoiBat;
    }

    public boolean isChonTable() {
        return chonTable;
    }

    public void setChonTable(boolean chonTable) {
        this.chonTable = chonTable;
    }
}
