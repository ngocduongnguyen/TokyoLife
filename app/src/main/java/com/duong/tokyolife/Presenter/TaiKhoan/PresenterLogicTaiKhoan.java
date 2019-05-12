package com.duong.tokyolife.Presenter.TaiKhoan;

import com.duong.tokyolife.Model.ObjectClass.NhanVien;
import com.duong.tokyolife.Model.TaiKhoan.TaiKhoanModel;
import com.duong.tokyolife.View.TaiKhoan.IViewThongtinTaiKhoan;

public class PresenterLogicTaiKhoan implements IPresenterTaiKhoan {

    IViewThongtinTaiKhoan iViewThongtinTaiKhoan;
    TaiKhoanModel taiKhoanModel;
    public PresenterLogicTaiKhoan(IViewThongtinTaiKhoan iViewThongtinTaiKhoan){
        this.iViewThongtinTaiKhoan = iViewThongtinTaiKhoan;
        taiKhoanModel = new TaiKhoanModel();
    }
    @Override
    public void layThongTintaiKhoan(long id) {
        NhanVien nhanVien = taiKhoanModel.layThongTinTaiKhoan(id);
        iViewThongtinTaiKhoan.hienThiThongTin(nhanVien);
    }

    @Override
    public void updateTaiKhoan(NhanVien nhanVien) {
        boolean kq = taiKhoanModel.updatetaiKhoan(nhanVien);
        if (kq)
            iViewThongtinTaiKhoan.thongBaoThanhCong();
        else
            iViewThongtinTaiKhoan.thongBaoThatBai();
    }
}
