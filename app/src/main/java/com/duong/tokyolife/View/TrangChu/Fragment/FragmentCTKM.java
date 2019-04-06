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
import android.widget.LinearLayout;

import com.duong.tokyolife.Adapter.KhuyenMaiAdapter;
import com.duong.tokyolife.Model.KhuyenMai.KhuyenMaiModel;
import com.duong.tokyolife.Model.ObjectClass.KhuyenMai;
import com.duong.tokyolife.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.TrangChu.IViewKhuyenMai;

import java.util.List;

public class FragmentCTKM extends Fragment implements IViewKhuyenMai {

    LinearLayout lnHinhKhuyenMai;
    RecyclerView recyclerView;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ctkm,container,false);

//        lnHinhKhuyenMai=view.findViewById(R.id.linearHinhKhuyenmai);
        recyclerView = view.findViewById(R.id.recyclerKhuyenMai);
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.layDanhSachKhuyenMai();

        return view;
    }

    @Override
    public void hienThiDanhSachKhuyenMai(List<KhuyenMai> ds) {
        KhuyenMaiAdapter khuyenMaiAdapter = new KhuyenMaiAdapter(getContext(),ds);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(khuyenMaiAdapter);
        recyclerView.setLayoutManager(layoutManager);
        khuyenMaiAdapter.notifyDataSetChanged();
    }
}