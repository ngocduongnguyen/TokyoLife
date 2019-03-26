package com.duong.tokyolife.Presenter.DanhGia;

import com.duong.tokyolife.Model.DanhGia.DanhGiaModel;
import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.View.DanhGia.IViewDanhGia;

public class PresenterLogicDanhGia implements IPresenterDanhGia {
    IViewDanhGia iViewDanhGia;
    DanhGiaModel danhGiaModel;
    public PresenterLogicDanhGia(IViewDanhGia iViewDanhGia){
        this.iViewDanhGia=iViewDanhGia;
        danhGiaModel = new DanhGiaModel();
    }
    @Override
    public void themDanhGia(DanhGia danhGia) {
        boolean kiemtra = danhGiaModel.themDanhGia(danhGia);
        if (kiemtra){
            iViewDanhGia.themThanhCong();
        } else {
            iViewDanhGia.themThatBai();
        }
    }
}
