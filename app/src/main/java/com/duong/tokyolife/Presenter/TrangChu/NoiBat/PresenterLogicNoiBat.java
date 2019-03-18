package com.duong.tokyolife.Presenter.TrangChu.NoiBat;

import com.duong.tokyolife.Model.ObjectClass.NoiBat;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
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
        NoiBat noiBat = new NoiBat();
        noiBat.setListThuongHieuNoiBat(noiBatModel.layDsThuongHieu());
        noiBat.setListLoaiSpNoiBat(noiBatModel.laySanPhamNoiBat());
        dsNoiBat.add(noiBat);

        if (noiBat.getListThuongHieuNoiBat().size()>0 && noiBat.getListLoaiSpNoiBat().size()>0){
            iViewNoiBat.hienThiDanhSachNoiBat(dsNoiBat);
        }
    }
}
