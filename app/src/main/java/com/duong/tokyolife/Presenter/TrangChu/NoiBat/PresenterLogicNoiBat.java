package com.duong.tokyolife.Presenter.TrangChu.NoiBat;

import com.duong.tokyolife.Model.ObjectClass.NoiBat;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Model.ObjectClass.ThuongHieu;
import com.duong.tokyolife.Model.TrangChu.NoiBatModel;
import com.duong.tokyolife.View.TrangChu.IViewNoiBat;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicNoiBat implements IPresenterNoiBat {

    NoiBatModel noiBatModel;
    IViewNoiBat iViewNoiBat;
    public PresenterLogicNoiBat(IViewNoiBat iViewNoiBat){
        this.iViewNoiBat=iViewNoiBat;
        noiBatModel = new NoiBatModel();
    }

    @Override
    public void layDanhSachDienTu() {
        List<NoiBat> dsNoiBat = new ArrayList<>();
        List<ThuongHieu> dsThuongHieu = noiBatModel.layDsThuongHieu();
        List<SanPham> dsSanPham = noiBatModel.laySanPhamNoiBat();
        NoiBat noiBat = new NoiBat();
        noiBat.setListThuongHieuNoiBat(dsThuongHieu);
        noiBat.setListLoaiSpNoiBat(dsSanPham);
        dsNoiBat.add(noiBat);
        if (dsThuongHieu.size()>0){
            iViewNoiBat.hienThiDanhSachNoiBat(dsNoiBat);
        }
    }
}
