package com.duong.tokyolife.Presenter.TrangChu.OptionMenuRight;

import android.content.Context;

import com.duong.tokyolife.Model.DangNhap_DangKy.DangNhapModel;
import com.duong.tokyolife.Model.GioHang.GioHangModel;
import com.facebook.AccessToken;

public class PresenterLogicOptionMenuFB implements IPresenterOptionMenuUsernameFB {

    @Override
    public AccessToken layAccesTokenFacebook() {
        DangNhapModel dangNhapModel = new DangNhapModel();
        return dangNhapModel.layTokenFBHienTai();
    }

    public int soLuongSanPhamTrongGioHang(Context context){
        GioHangModel gioHangModel = new GioHangModel();
        return gioHangModel.layDSsPTrongGioHang(context).size();
    }
}
