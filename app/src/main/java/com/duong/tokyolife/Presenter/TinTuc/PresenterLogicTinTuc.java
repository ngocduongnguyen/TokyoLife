package com.duong.tokyolife.Presenter.TinTuc;

import com.duong.tokyolife.Model.TinTuc.TinTucModel;
import com.duong.tokyolife.View.TrangChu.IViewTinTuc;

public class PresenterLogicTinTuc implements IPresenterTinTuc{

    IViewTinTuc iViewTinTuc;
    TinTucModel tinTucModel;
    public PresenterLogicTinTuc(IViewTinTuc iViewTinTuc){
        this.iViewTinTuc=iViewTinTuc;
        tinTucModel = new TinTucModel();
    }
    @Override
    public void layDanhSachBaiViet() {
        if (tinTucModel.layDanhSachBaiViet().size()>0)
            iViewTinTuc.hienThiDanhSachBaiViet(tinTucModel.layDanhSachBaiViet());
    }
}
