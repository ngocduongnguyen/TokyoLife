package com.duong.tokyolife.Presenter.SanPhamMoi;

import com.duong.tokyolife.Model.SanPhamMoi.SanPhamMoiModel;
import com.duong.tokyolife.Presenter.KhuyenMai.IPresenterKhuyenMai;
import com.duong.tokyolife.View.TrangChu.IViewSanPhamMoi;

public class PresenterLogicSanPhamMoi implements IPresenterSanPhamMoi {
    IViewSanPhamMoi iViewSanPhamMoi;
    SanPhamMoiModel sanPhamMoiModel;
    public PresenterLogicSanPhamMoi(IViewSanPhamMoi iViewSanPhamMoi){
        this.iViewSanPhamMoi=iViewSanPhamMoi;
        sanPhamMoiModel = new SanPhamMoiModel();
    }
    @Override
    public void layDanhSachSanPham() {
        if (sanPhamMoiModel.layDanhSachSpMoi().size()>0)
            iViewSanPhamMoi.hienThiDanhSach(sanPhamMoiModel.layDanhSachSpMoi());
    }
}
