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
import android.widget.ImageView;

import com.duong.tokyolife.Adapter.NoiBatAdapter;
import com.duong.tokyolife.Model.ObjectClass.NoiBat;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Model.ObjectClass.ThuongHieu;
import com.duong.tokyolife.Model.TrangChu.NoiBatModel;
import com.duong.tokyolife.Presenter.TrangChu.NoiBat.PresenterLogicNoiBat;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.TrangChu.IViewNoiBat;

import java.util.ArrayList;
import java.util.List;

public class FragmentNoiBat extends Fragment implements IViewNoiBat {

    RecyclerView recyclerView;
    PresenterLogicNoiBat presenterLogicNoiBat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noibat,container,false);
        recyclerView = view.findViewById(R.id.recycler_noibat_tong);
        presenterLogicNoiBat =  new PresenterLogicNoiBat(this);
        presenterLogicNoiBat.layDanhSachDienTu();
        return view;
    }

    @Override
    public void hienThiDanhSachNoiBat(List<NoiBat> dsNoiBat) {
        NoiBatAdapter noiBatAdapter = new NoiBatAdapter(getContext(),dsNoiBat);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(noiBatAdapter);
        recyclerView.setLayoutManager(layoutManager);
        noiBatAdapter.notifyDataSetChanged();
    }
}