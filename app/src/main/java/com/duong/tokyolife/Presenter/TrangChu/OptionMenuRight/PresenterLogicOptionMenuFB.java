package com.duong.tokyolife.Presenter.TrangChu.OptionMenuRight;

import com.duong.tokyolife.Model.DangNhap_DangKy.DangNhapModel;
import com.facebook.AccessToken;

public class PresenterLogicOptionMenuFB implements IPresenterOptionMenuUsernameFB {
    @Override
    public AccessToken layAccesTokenFacebook() {
        DangNhapModel dangNhapModel = new DangNhapModel();
        return dangNhapModel.layTokenFBHienTai();
    }
}
