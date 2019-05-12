package com.duong.tokyolife.View.QLDonHang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.duong.tokyolife.Adapter.DanhSachDonHangAdapter;
import com.duong.tokyolife.Model.ObjectClass.DonHang;
import com.duong.tokyolife.Model.ObjectClass.LoaiSanPham;
import com.duong.tokyolife.Model.QLDonHang.QLDonHangModel;
import com.duong.tokyolife.Presenter.QLDonHang.PresenterLogicQLDH;
import com.duong.tokyolife.R;

import java.util.List;

public class QLDonHangActivity extends AppCompatActivity implements IViewQLDH{

    Toolbar toolbar;
    ImageButton btnHome;
    RecyclerView recyclerView;
    PresenterLogicQLDH presenterLogicQLDH;
    long idkhachhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qldon_hang);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerDonHang);
        presenterLogicQLDH = new PresenterLogicQLDH(this);
        idkhachhang = Long.parseLong(getIntent().getStringExtra("idkhachhang"));
        presenterLogicQLDH.layDonHang(idkhachhang);
    }

    @Override
    public void hienThiDanhSachDonHang(List<DonHang> ds) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(QLDonHangActivity.this);
        DanhSachDonHangAdapter danhSachDonHangAdapter = new DanhSachDonHangAdapter(this,idkhachhang,QLDonHangActivity.this,ds);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(danhSachDonHangAdapter);
        danhSachDonHangAdapter.notifyDataSetChanged();
    }

    @Override
    public void thongBao() {
        Toast.makeText(getApplicationContext(),"Danh sách hóa đơn của bạn trống!",Toast.LENGTH_SHORT).show();
    }
}
