package com.duong.tokyolife.Presenter.ChiTietBaiViet;

import com.duong.tokyolife.Model.TinTuc.TinTucModel;
import com.duong.tokyolife.View.ChiTietBaiViet.IViewChiTietBaiViet;

public class PresenterLogicChiTietBaiViet implements IPresenterChiTietBaiViet {

    IViewChiTietBaiViet iViewChiTietBaiViet;
    TinTucModel tinTucModel;
    public PresenterLogicChiTietBaiViet(IViewChiTietBaiViet iViewChiTietBaiViet){
        this.iViewChiTietBaiViet=iViewChiTietBaiViet;
        tinTucModel = new TinTucModel();
    }

    @Override
    public void layChihTietBaiViet(int matintuc) {
        if (tinTucModel.layChiTietBaiViet(matintuc)!=null)
            iViewChiTietBaiViet.hienThiChiTietBaiViet(tinTucModel.layChiTietBaiViet(matintuc));
    }
}
