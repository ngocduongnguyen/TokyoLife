package com.duong.tokyolife.Presenter.DangNhap_DangKy;

import com.duong.tokyolife.Model.DangNhap_DangKy.DangKyModel;
import com.duong.tokyolife.Model.ObjectClass.NhanVien;
import com.duong.tokyolife.View.DangNhap_DangKy.Fragment.IViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy{

    IViewDangKy iViewDangKy;
    DangKyModel dangKyModel;
    public PresenterLogicDangKy(IViewDangKy iViewDangKy){
        this.iViewDangKy=iViewDangKy;
    }

    @Override
    public void dangKyThanhVien(NhanVien nhanVien) {

        dangKyModel = new DangKyModel();
        boolean kiemTra = dangKyModel.dangKyThanhVien(nhanVien);

        if (kiemTra){
            iViewDangKy.dangKyThanhCong();
        } else{
            iViewDangKy.dangKyThatBai();
        }

    }
}
