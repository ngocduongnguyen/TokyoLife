package com.duong.tokyolife.Presenter.QLDonHang;

import com.duong.tokyolife.Model.QLDonHang.QLDonHangModel;
import com.duong.tokyolife.View.QLDonHang.IViewQLDH;

public class PresenterLogicQLDH implements IPresenterQLDH{

    IViewQLDH iViewQLDH;
    QLDonHangModel qlDonHangModel;
    public PresenterLogicQLDH(IViewQLDH iViewQLDH){
        qlDonHangModel = new QLDonHangModel();
        this.iViewQLDH = iViewQLDH;
    }
    @Override
    public void layDonHang(long idkhachhang) {
        if (qlDonHangModel.layDanhSachDonHang(idkhachhang).size()>0)
            iViewQLDH.hienThiDanhSachDonHang(qlDonHangModel.layDanhSachDonHang(idkhachhang));
        else
            iViewQLDH.thongBao();
    }
}
