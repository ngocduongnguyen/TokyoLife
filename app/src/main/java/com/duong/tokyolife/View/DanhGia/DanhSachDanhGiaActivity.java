package com.duong.tokyolife.View.DanhGia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.duong.tokyolife.Adapter.DsDanhGiaSanPhamAdapter;
import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.Presenter.DanhSachDanhGia.PresenterDSDanhGia;
import com.duong.tokyolife.R;

import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements IViewDanhSachDanhGia {

    int masp=0;
    RecyclerView recyclerView;
    PresenterDSDanhGia presenterDSDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_danh_gia);
        masp=getIntent().getIntExtra("masp",0);
        recyclerView=findViewById(R.id.recycler_danhgia);
        presenterDSDanhGia = new PresenterDSDanhGia(this);
        presenterDSDanhGia.laydsDanhGiaTheoSP(masp,0);
    }

    @Override
    public void hienThiDanhSachDanhGia(List<DanhGia> ds) {
        DsDanhGiaSanPhamAdapter dsDanhGiaSanPhamAdapter = new DsDanhGiaSanPhamAdapter(DanhSachDanhGiaActivity.this,ds,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DanhSachDanhGiaActivity.this);
        recyclerView.setAdapter(dsDanhGiaSanPhamAdapter);
        recyclerView.setLayoutManager(layoutManager);
        dsDanhGiaSanPhamAdapter.notifyDataSetChanged();
    }
}
