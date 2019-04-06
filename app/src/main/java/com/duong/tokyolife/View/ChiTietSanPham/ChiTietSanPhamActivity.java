package com.duong.tokyolife.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.duong.tokyolife.Adapter.DsDanhGiaSanPhamAdapter;
import com.duong.tokyolife.Adapter.ViewPagerSliderAdapter;
import com.duong.tokyolife.Model.GioHang.DataSanPham;
import com.duong.tokyolife.Model.ObjectClass.ChiTietSanPham;
import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.duong.tokyolife.Presenter.DanhGia.PresenterLogicDanhGia;
import com.duong.tokyolife.Presenter.TrangChu.OptionMenuRight.PresenterLogicOptionMenuFB;
import com.duong.tokyolife.R;
import com.duong.tokyolife.Utils.Data;
import com.duong.tokyolife.Utils.ServerName;
import com.duong.tokyolife.View.DangNhap_DangKy.DangNhap_DangKyActivity;
import com.duong.tokyolife.View.DanhGia.DanhSachDanhGiaActivity;
import com.duong.tokyolife.View.DanhGia.ThemDanhGiaActivity;
import com.duong.tokyolife.View.GioHang.GioHangActivity;
import com.duong.tokyolife.View.ThanhToan.ThanhToanActivity;
import com.duong.tokyolife.View.TrangChu.MainActivity;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements IViewChiTietSanPham{

    Toolbar toolbar;
    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    PresenterLogicOptionMenuFB presenterLogicOptionMenuFB;
    List<Fragment> dsFragment;
    TextView[] txtDots;
    LinearLayout layoutDotsChiTiet,layoutThongSoKT;
    TextView txtTenSanPham,txtGiaTien,txtThongtinChiTiet,txtVietDanhGia,txtXemtatcadanhgia,txt_giohangsl;
    RecyclerView recyclerView;
    RatingBar ratingBar;
    ImageView imgThemGioHang;
    Button btnMuaNgay;

    SanPham sanPhamGioHang;
    Menu menu;
    boolean onpause = false;

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
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        txtThongtinChiTiet = findViewById(R.id.txtThongtinChiTiet);
        layoutThongSoKT = findViewById(R.id.linearThongSoKT);
        recyclerView = findViewById(R.id.recycler_danhgia);
        txtXemtatcadanhgia = findViewById(R.id.txt_xemtatcadanhgia);
        imgThemGioHang = findViewById(R.id.imgThemGioHang);

        txtVietDanhGia=findViewById(R.id.txt_vietDanhGia);

        btnMuaNgay = findViewById(R.id.btnMuangay);

        presenterLogicOptionMenuFB = new PresenterLogicOptionMenuFB();

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

        //thêm giỏ hàng
        imgThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = dsFragment.get(0);
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.img_slider_chitiet);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhGioHang(hinhsanphamgiohang);
                sanPhamGioHang.setSoluong(1);
                presenterLogicChiTietSanPham.themGioHang(sanPhamGioHang,ChiTietSanPhamActivity.this);
            }
        });

        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Data.code.equals("")){
                    Toast.makeText(ChiTietSanPhamActivity.this,"Bạn phải đăng nhập để mua hàng!",Toast.LENGTH_SHORT).show();
                    Intent iDangNhap = new Intent(ChiTietSanPhamActivity.this, DangNhap_DangKyActivity.class);
                    startActivity(iDangNhap);
                } else {
                Fragment fragment = dsFragment.get(0);
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.img_slider_chitiet);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhGioHang(hinhsanphamgiohang);
                sanPhamGioHang.setSoluong(1);
                presenterLogicChiTietSanPham.themGioHang(sanPhamGioHang,ChiTietSanPhamActivity.this);

                Intent iThanhToan = new Intent(ChiTietSanPhamActivity.this,ThanhToanActivity.class);
                startActivity(iThanhToan);
                }
            }
        });
    }

    @Override
    public void hienThiChiTietSanPham(SanPham sanPham) {

        sanPhamGioHang = sanPham;
        sanPhamGioHang.setSoluongtonkho(sanPham.getSoluongtonkho());
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

    @Override
    public void themGioHangThanhCong() {
        Toast.makeText(ChiTietSanPhamActivity.this,"Thêm vào giở hàng thành công!",Toast.LENGTH_SHORT).show();
        txt_giohangsl.setText(String.valueOf(presenterLogicOptionMenuFB.soLuongSanPhamTrongGioHang(ChiTietSanPhamActivity.this)));
    }

    @Override
    public void themGioHangThatBai() {
        Toast.makeText(ChiTietSanPhamActivity.this,"Sản phẩm đã có trong giỏ hàng!",Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phai_trangchu,menu);
        this.menu=menu;
        MenuItem itemGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itemGioHang);
        txt_giohangsl = giaoDienCustomGioHang.findViewById(R.id.txtGioHangSL);
        txt_giohangsl.setText(String.valueOf(presenterLogicOptionMenuFB.soLuongSanPhamTrongGioHang(ChiTietSanPhamActivity.this)));
        txt_giohangsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onpause){
            MenuItem itemGioHang = menu.findItem(R.id.itGioHang);
            View giaoDienCustomGioHang = MenuItemCompat.getActionView(itemGioHang);
            txt_giohangsl = giaoDienCustomGioHang.findViewById(R.id.txtGioHangSL);
            txt_giohangsl.setText(String.valueOf(presenterLogicOptionMenuFB.soLuongSanPhamTrongGioHang(ChiTietSanPhamActivity.this)));
            txt_giohangsl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                    startActivity(iGioHang);
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onpause = true;
    }
}