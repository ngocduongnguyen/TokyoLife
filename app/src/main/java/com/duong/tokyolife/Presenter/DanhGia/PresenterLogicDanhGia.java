package com.duong.tokyolife.Presenter.DanhGia;

import com.duong.tokyolife.Model.DanhGia.DanhGiaModel;
import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.Utils.Data;
import com.duong.tokyolife.View.DanhGia.IViewThemDanhGiaDanhGia;

public class PresenterLogicDanhGia implements IPresenterDanhGia {
    IViewThemDanhGiaDanhGia iViewThemDanhGiaDanhGia;
    DanhGiaModel danhGiaModel;
    public PresenterLogicDanhGia(IViewThemDanhGiaDanhGia iViewThemDanhGiaDanhGia){
        this.iViewThemDanhGiaDanhGia = iViewThemDanhGiaDanhGia;
        danhGiaModel = new DanhGiaModel();
    }
    @Override
    public void themDanhGia(DanhGia danhGia) {
        if (!Data.code.equals("")){
            boolean kiemtra = danhGiaModel.themDanhGia(danhGia);
            if (kiemtra){
                iViewThemDanhGiaDanhGia.themThanhCong();
            } else {
                iViewThemDanhGiaDanhGia.themThatBai();
            }
        } else {
            iViewThemDanhGiaDanhGia.themThatBai();
        }
    }
}
