package com.duong.tokyolife.View.ChiTietSanPham;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duong.tokyolife.Adapter.ViewPagerSliderAdapter;
import com.duong.tokyolife.Model.ChiTietSanPham.ChiTietSanPhamModel;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.duong.tokyolife.R;
import com.duong.tokyolife.Utils.ServerName;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements IViewChiTietSanPham{

    Toolbar toolbar;
    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    List<Fragment> dsFragment;
    TextView[] txtDots;
    LinearLayout layoutDotsChiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        viewPager = findViewById(R.id.viewPagerSliderShow);
        layoutDotsChiTiet = findViewById(R.id.layout_dots_chitiet);

        Intent intent = getIntent();
        int masp = intent.getIntExtra("masp",0);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.layChiTietSanPham(masp);

    }

    @Override
    public void hienThiChiTietSanPham(SanPham sanPham) {

    }

    @Override
    public void hienThiSliderSanPham(String[] linkHinhAnh) {
        dsFragment = new ArrayList<>();
        for (int i = 0; i<linkHinhAnh.length; i++){
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkHinh",ServerName.SERVER_IMG+linkHinhAnh[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);
            dsFragment.add(fragmentSliderChiTietSanPham);
        }

        ViewPagerSliderAdapter viewPagerSliderAdapter = new ViewPagerSliderAdapter(getSupportFragmentManager(),dsFragment);
        viewPager.setAdapter(viewPagerSliderAdapter);
        viewPagerSliderAdapter.notifyDataSetChanged();
        themDotSlider(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                themDotSlider(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void themDotSlider(int viTriHienTai){
        txtDots = new TextView[dsFragment.size()];
        layoutDotsChiTiet.removeAllViews();
        for (int i = 0; i<dsFragment.size(); i++){
            txtDots[i]=new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(30);
            txtDots[i].setTextColor(getIDColor(R.color.colorDotSliderInNonActive));
            layoutDotsChiTiet.addView(txtDots[i]);
        }
        txtDots[viTriHienTai].setTextColor(getIDColor(R.color.colorDotSliderInActive));
    }

    public int getIDColor(int idcolor){
        int color = 0;
        if (Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idcolor);
        } else{
            color = getResources().getColor(idcolor);
        }
        return color;
    }
}
