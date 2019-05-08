package com.duong.tokyolife.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duong.tokyolife.Adapter.DanhSachSanPhamAdapter;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.SanPhamMoi.PresenterLogicSanPhamMoi;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.TrangChu.IViewSanPhamMoi;

import java.util.List;

public class FragmentSanPhamMoi extends Fragment implements IViewSanPhamMoi {
    RecyclerView recyclerView;
    PresenterLogicSanPhamMoi presenterLogicSanPhamMoi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanphammoi,container,false);
        presenterLogicSanPhamMoi = new PresenterLogicSanPhamMoi(this);
        recyclerView = view.findViewById(R.id.recyclerSanPhamMoi);
        presenterLogicSanPhamMoi.layDanhSachSanPham();
        return view;
    }

    @Override
    public void hienThiDanhSach(List<SanPham> ds) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        DanhSachSanPhamAdapter danhSachSanPhamAdapter = new DanhSachSanPhamAdapter(getContext(),ds);
        recyclerView.setAdapter(danhSachSanPhamAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
