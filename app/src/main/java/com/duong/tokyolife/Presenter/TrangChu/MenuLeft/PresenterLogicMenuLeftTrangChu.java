package com.duong.tokyolife.Presenter.TrangChu.MenuLeft;

import com.duong.tokyolife.Model.TrangChu.DataJSONMenuLeft;
import com.duong.tokyolife.View.TrangChu.IViewTrangChu;

public class PresenterLogicMenuLeftTrangChu implements IPresenterMenuLeftTrangChu {

    DataJSONMenuLeft dataJSONMenuLeft;
    IViewTrangChu iViewTrangChu;

    public PresenterLogicMenuLeftTrangChu(IViewTrangChu iViewTrangChu){
        this.iViewTrangChu=iViewTrangChu;
        dataJSONMenuLeft = new DataJSONMenuLeft();
    }

    @Override
    public void layDanhSachLoaiSP() {
        iViewTrangChu.hienThiDS_MenuLeft(dataJSONMenuLeft.layDanhSachLoaiSP());
    }
}
