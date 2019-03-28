package com.duong.tokyolife.View.ChiTietSanPham;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.duong.tokyolife.Adapter.DsDanhGiaSanPhamAdapter;
import com.duong.tokyolife.Adapter.ViewPagerSliderAdapter;
import com.duong.tokyolife.Model.ObjectClass.ChiTietSanPham;
import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.duong.tokyolife.Presenter.DanhGia.PresenterLogicDanhGia;
import com.duong.tokyolife.R;
import com.duong.tokyolife.Utils.ServerName;
import com.duong.tokyolife.View.DanhGia.DanhSachDanhGiaActivity;
import com.duong.tokyolife.View.DanhGia.ThemDanhGiaActivity;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements IViewChiTietSanPham{

    Toolbar toolbar;
    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    List<Fragment> dsFragment;
    TextView[] txtDots;
    LinearLayout layoutDotsChiTiet,layoutThongSoKT;
    TextView txtTenSanPham,txtGiaTien,txtThongtinChiTiet,txtVietDanhGia,txtXemtatcadanhgia;
    RecyclerView recyclerView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        viewPager = findViewById(R.id.viewPagerSliderShow);
        layoutDotsChiTiet = findViewById(R.id.layout_dots_chitiet);
        txtTenSanPham = findViewById(R.id.txt_tenSanPham);
        txtGiaTien = findViewById(R.id.txt_giaSanPham);
        ratingBar=findViewById(R.id.rbDanhGia);
        toolbar = findViewById(R.id.toolbar);
        txtThongtinChiTiet = findViewById(R.id.txtThongtinChiTiet);
        layoutThongSoKT = findViewById(R.id.linearThongSoKT);
        recyclerView = findViewById(R.id.recycler_danhgia);
        txtXemtatcadanhgia = findViewById(R.id.txt_xemtatcadanhgia);

        txtVietDanhGia=findViewById(R.id.txt_vietDanhGia);

        Intent intent = getIntent();
        final int masp = intent.getIntExtra("masp",0);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.layChiTietSanPham(masp);
        presenterLogicChiTietSanPham.laydsDanhGiaTheoSP(masp,0);
        presenterLogicChiTietSanPham.layTBDanhGiaSP(masp);

        txtVietDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDanhGia = new Intent(ChiTietSanPhamActivity.this, ThemDanhGiaActivity.class);
                iDanhGia.putExtra("masp",masp);
                startActivity(iDanhGia);
            }
        });

        txtXemtatcadanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDsDanhGia = new Intent(ChiTietSanPhamActivity.this, DanhSachDanhGiaActivity.class);
                iDsDanhGia.putExtra("masp",masp);
                startActivity(iDsDanhGia);
            }
        });
    }

    @Override
    public void hienThiChiTietSanPham(SanPham sanPham) {
        txtTenSanPham.setText(sanPham.getTensp());
        Format format = new DecimalFormat("###,###");
        String gia = ((DecimalFormat) format).format(sanPham.getGia());
        txtGiaTien.setText(gia+" VNĐ");
        txtThongtinChiTiet.setText(sanPham.getThongtin());

        //tạo thông số kỹ thuật
        List<ChiTietSanPham> dsCT = sanPham.getDsChiTietSP();
        for (int i=0;i<dsCT.size();i++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtTenThongSo.setText(dsCT.get(i).getChiTietSP());
            txtTenThongSo.setPadding(20,0,0,0);

            TextView txtGiaTri = new TextView(this);
            txtGiaTri.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtGiaTri.setText(dsCT.get(i).getGiaTri());

            linearLayout.addView(txtTenThongSo);
            linearLayout.addView(txtGiaTri);

            layoutThongSoKT.addView(linearLayout);
        }
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


    @Override
    public void hienThiDanhGia(List<DanhGia> ds) {
        DsDanhGiaSanPhamAdapter dsDanhGiaSanPhamAdapter = new DsDanhGiaSanPhamAdapter(ChiTietSanPhamActivity.this,ds,2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ChiTietSanPhamActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dsDanhGiaSanPhamAdapter);
        dsDanhGiaSanPhamAdapter.notifyDataSetChanged();
    }

    @Override
    public void layTBSoSao(float count) {
        ratingBar.setRating(count);
    }

}