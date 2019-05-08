package com.duong.tokyolife.Presenter.KhuyenMai;

import com.duong.tokyolife.Model.KhuyenMai.KhuyenMaiModel;
import com.duong.tokyolife.View.TrangChu.IViewKhuyenMai;

public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai {

    IViewKhuyenMai iViewKhuyenMai;
    KhuyenMaiModel khuyenMaiModel;

    public PresenterLogicKhuyenMai(IViewKhuyenMai iViewKhuyenMai){
        this.iViewKhuyenMai=iViewKhuyenMai;
        khuyenMaiModel = new KhuyenMaiModel();
    }

    @Override
    public void layDSkhuyenmai() {
        if (khuyenMaiModel.layDanhSachSPKM().size()>0)
        iViewKhuyenMai.hienThiDanhSach(khuyenMaiModel.layDanhSachSPKM());
    }
}