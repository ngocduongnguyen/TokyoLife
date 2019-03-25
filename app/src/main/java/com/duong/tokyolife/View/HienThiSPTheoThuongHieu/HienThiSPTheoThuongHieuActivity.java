package com.duong.tokyolife.View.HienThiSPTheoThuongHieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.duong.tokyolife.Adapter.DsSPTheoThuongHieuAdapter;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.HienThiSPTheoThuongHieu.PresenterLogicHienThiSPTheoThuongHieu;
import com.duong.tokyolife.R;
import com.duong.tokyolife.Utils.LoadMore;

import java.util.List;

public class HienThiSPTheoThuongHieuActivity extends AppCompatActivity implements IViewHienThiSPTheoThuongHieu{

    RecyclerView recyclerView;
    PresenterLogicHienThiSPTheoThuongHieu presenterLogicHienThiSPTheoThuongHieu;
    DsSPTheoThuongHieuAdapter dsSPTheoThuongHieuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_sptheo_thuong_hieu);

        Intent intent = getIntent();
        int math = intent.getIntExtra("dsspofth",0);
        recyclerView=findViewById(R.id.recyclerDsSpByThuongHieu);
        presenterLogicHienThiSPTheoThuongHieu = new PresenterLogicHienThiSPTheoThuongHieu(this);
        presenterLogicHienThiSPTheoThuongHieu.layDSspTheoThuogHieu(math);

    }

    @Override
    public void hienThiDSSanPham(List<SanPham> sanPhamList) {
        dsSPTheoThuongHieuAdapter = new DsSPTheoThuongHieuAdapter(HienThiSPTheoThuongHieuActivity.this,sanPhamList);
    //    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HienThiSPTheoThuongHieuActivity.this,2,GridLayoutManager.VERTICAL,false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HienThiSPTheoThuongHieuActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dsSPTheoThuongHieuAdapter);
//        recyclerView.addOnScrollListener(new LoadMore(layoutManager));
        dsSPTheoThuongHieuAdapter.notifyDataSetChanged();
    }

    @Override
    public void loiHienThiDSSanPham() {
        Toast.makeText(HienThiSPTheoThuongHieuActivity.this,"Danh sách sản phẩm trống!",Toast.LENGTH_SHORT).show();
    }
}