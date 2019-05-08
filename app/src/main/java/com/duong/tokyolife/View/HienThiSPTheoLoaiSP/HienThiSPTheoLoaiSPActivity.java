package com.duong.tokyolife.View.HienThiSPTheoLoaiSP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.duong.tokyolife.Adapter.DanhSachSanPhamAdapter;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.HienThiSPTheoLoaiSP.PresenterLogicHienThiSPTheoLoaiSP;
import com.duong.tokyolife.R;

import java.util.List;

public class HienThiSPTheoLoaiSPActivity extends AppCompatActivity implements IViewHienThiSPTheoLoaiSP{
    RecyclerView recyclerView;
    DanhSachSanPhamAdapter danhSachSanPhamAdapter;
    PresenterLogicHienThiSPTheoLoaiSP presenterLogicHienThiSPTheoLoaiSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_sptheo_loai_sp);

        recyclerView=findViewById(R.id.recyclerDsSpByLoaiSP);

        Intent intent = getIntent();
        int maloaisp = intent.getIntExtra("maloaisp",0);

        presenterLogicHienThiSPTheoLoaiSP = new PresenterLogicHienThiSPTheoLoaiSP(HienThiSPTheoLoaiSPActivity.this);
        presenterLogicHienThiSPTheoLoaiSP.layDanhSachSanPhamTheoMaLoai(maloaisp);

    }

    @Override
    public void hienThiSanPhamTheoLoaiSP(List<SanPham> dsSanPham) {
        danhSachSanPhamAdapter = new DanhSachSanPhamAdapter(HienThiSPTheoLoaiSPActivity.this,dsSanPham);
        recyclerView.setAdapter(danhSachSanPhamAdapter);
    //    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HienThiSPTheoLoaiSPActivity.this,2, GridLayoutManager.VERTICAL,false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HienThiSPTheoLoaiSPActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        danhSachSanPhamAdapter.notifyDataSetChanged();
    }

    @Override
    public void hienThiLoi() {
        Toast.makeText(HienThiSPTheoLoaiSPActivity.this,"Danh sách sản phẩm trống!",Toast.LENGTH_SHORT).show();
    }
}
