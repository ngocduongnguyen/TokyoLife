package com.duong.tokyolife.View.GioHang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.duong.tokyolife.Adapter.GioHangAdapter;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.GioHang.PresenterLogicGioHang;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.duong.tokyolife.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements IViewGioHang{

    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    Button btnMuaNgay;
    ImageButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        recyclerView = findViewById(R.id.recycler_giohang);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.layDanhSachGioHang(this);
        btnMuaNgay = findViewById(R.id.btnMuangay);
        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMua = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(iMua);
            }
        });

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void hienThiDsSanPhamTrongGioHang(List<SanPham> ds) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        GioHangAdapter gioHangAdapter = new GioHangAdapter(GioHangActivity.this,ds);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gioHangAdapter);
        gioHangAdapter.notifyDataSetChanged();
    }
}
