package com.duong.tokyolife.View.HienThiSPTheoThuongHieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.duong.tokyolife.Adapter.DanhSachSanPhamAdapter;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.HienThiSPTheoThuongHieu.PresenterLogicHienThiSPTheoThuongHieu;
import com.duong.tokyolife.R;

import java.util.List;

public class HienThiSPTheoThuongHieuActivity extends AppCompatActivity implements IViewHienThiSPTheoThuongHieu{

    RecyclerView recyclerView;
    PresenterLogicHienThiSPTheoThuongHieu presenterLogicHienThiSPTheoThuongHieu;
    DanhSachSanPhamAdapter danhSachSanPhamAdapter;
    ImageView iconBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_sptheo_thuong_hieu);

        Intent intent = getIntent();
        int math = intent.getIntExtra("dsspofth",0);
        recyclerView=findViewById(R.id.recyclerDsSpByThuongHieu);
        presenterLogicHienThiSPTheoThuongHieu = new PresenterLogicHienThiSPTheoThuongHieu(this);
        presenterLogicHienThiSPTheoThuongHieu.layDSspTheoThuogHieu(math);

        iconBack = findViewById(R.id.btnHome);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void hienThiDSSanPham(List<SanPham> sanPhamList) {
        danhSachSanPhamAdapter = new DanhSachSanPhamAdapter(HienThiSPTheoThuongHieuActivity.this,sanPhamList);
    //    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HienThiSPTheoThuongHieuActivity.this,2,GridLayoutManager.VERTICAL,false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HienThiSPTheoThuongHieuActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(danhSachSanPhamAdapter);
//        recyclerView.addOnScrollListener(new LoadMore(layoutManager));
        danhSachSanPhamAdapter.notifyDataSetChanged();
    }

    @Override
    public void loiHienThiDSSanPham() {
        Toast.makeText(HienThiSPTheoThuongHieuActivity.this,"Danh sách sản phẩm trống!",Toast.LENGTH_SHORT).show();
    }
}