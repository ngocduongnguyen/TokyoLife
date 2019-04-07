package com.duong.tokyolife.Presenter.TimKiem;

import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Model.TimKiem.TimKiemModel;
import com.duong.tokyolife.View.TimKiem.IViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem implements IPresenterTimKiem{

    IViewTimKiem iViewTimKiem;
    TimKiemModel timKiemModel;
    public PresenterLogicTimKiem(IViewTimKiem iViewTimKiem){
        this.iViewTimKiem=iViewTimKiem;
        timKiemModel = new TimKiemModel();
    }

    @Override
    public void laydanhsachtimkiem(String tensp) {
        if (timKiemModel.layDanhSachSPtheoLoaiSP(tensp).size()>0){
            List<SanPham> list = timKiemModel.layDanhSachSPtheoLoaiSP(tensp);
            iViewTimKiem.hienthidanhsachtimkiem(list);
        } else {
            iViewTimKiem.timkiemthatbai();
        }
    }
}
