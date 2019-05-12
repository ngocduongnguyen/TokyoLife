package com.duong.tokyolife.Presenter.TaiKhoan;

import com.duong.tokyolife.Model.ObjectClass.NhanVien;

public interface IPresenterTaiKhoan {
    void layThongTintaiKhoan(long id);
    void updateTaiKhoan(NhanVien nhanVien);
}
