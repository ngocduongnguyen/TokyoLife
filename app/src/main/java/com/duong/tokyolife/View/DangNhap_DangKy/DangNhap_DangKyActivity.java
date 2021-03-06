package com.duong.tokyolife.View.DangNhap_DangKy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.duong.tokyolife.Adapter.ViewPagerDangKyDangNhapAdapter;
import com.duong.tokyolife.Model.ChiTietSanPham.ChiTietSanPhamModel;
import com.duong.tokyolife.R;

public class DangNhap_DangKyActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerDangKyDangNhapAdapter viewPagerDangKyDangNhapAdapter;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap__dang_ky);
        addControls();
    }

    private void addControls() {

        toolbar=findViewById(R.id.toolbarDN_DK);
        tabLayout=findViewById(R.id.tabsDN_DK);
        viewPager=findViewById(R.id.viewPagerDN_DK);
        imageButton=findViewById(R.id.btnHome);

        toolbar.setTitle("Đăng nhập - Đăng ký");
        setSupportActionBar(toolbar);

        viewPagerDangKyDangNhapAdapter = new ViewPagerDangKyDangNhapAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerDangKyDangNhapAdapter);
        viewPagerDangKyDangNhapAdapter.notifyDataSetChanged();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        tabLayout.setupWithViewPager(viewPager);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
