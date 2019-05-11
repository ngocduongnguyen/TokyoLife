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

import com.duong.tokyolife.Adapter.BaiVietAdapter;
import com.duong.tokyolife.Model.ObjectClass.BaiViet;
import com.duong.tokyolife.Presenter.TinTuc.PresenterLogicTinTuc;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.TrangChu.IViewTinTuc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FragmentTinTuc extends Fragment implements IViewTinTuc {
    RecyclerView recyclerView;
    PresenterLogicTinTuc presenterLogicTinTuc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tintuc,container,false);

        recyclerView = view.findViewById(R.id.recylerTintuc);
        presenterLogicTinTuc = new PresenterLogicTinTuc(this);
        presenterLogicTinTuc.layDanhSachBaiViet();

        return view;
    }

    @Override
    public void hienThiDanhSachBaiViet(List<BaiViet> ds) {

        BaiVietAdapter baiVietAdapter = new BaiVietAdapter(getContext(),ds);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(baiVietAdapter);
        recyclerView.setLayoutManager(layoutManager);
        baiVietAdapter.notifyDataSetChanged();

    }
}