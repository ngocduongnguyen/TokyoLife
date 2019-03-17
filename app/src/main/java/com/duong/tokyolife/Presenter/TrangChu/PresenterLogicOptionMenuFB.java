package com.duong.tokyolife.Presenter.TrangChu;

import android.util.Log;

import com.duong.tokyolife.Model.TrangChu.ModelDangNhap;
import com.facebook.AccessToken;

public class PresenterLogicOptionMenuFB implements IPresenterOptionMenuUsernameFB {
    @Override
    public AccessToken layAccesTokenFacebook() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        return modelDangNhap.layTokenFBHienTai();
    }
}
