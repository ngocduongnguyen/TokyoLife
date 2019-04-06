package com.duong.tokyolife.Presenter.KhuyenMai;

import com.duong.tokyolife.Model.KhuyenMai.KhuyenMaiModel;
import com.duong.tokyolife.Model.ObjectClass.KhuyenMai;
import com.duong.tokyolife.View.TrangChu.IViewKhuyenMai;

import java.util.List;

public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai {

    IViewKhuyenMai iViewKhuyenMai;
    KhuyenMaiModel khuyenMaiModel;
    public PresenterLogicKhuyenMai(IViewKhuyenMai iViewKhuyenMai){
        this.iViewKhuyenMai=iViewKhuyenMai;
        khuyenMaiModel = new KhuyenMaiModel();
    }

    @Override
    public void layDanhSachKhuyenMai() {
        List<KhuyenMai> ds = khuyenMaiModel.layDsKhuyenMai();
        if (ds.size()>0){
            iViewKhuyenMai.hienThiDanhSachKhuyenMai(ds);
        }
    }
}
