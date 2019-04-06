package com.duong.tokyolife.View.TrangChu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.duong.tokyolife.Adapter.MenuLoaiSanPhamAdapter;
import com.duong.tokyolife.Adapter.ViewPagerTrangChuAdapter;
import com.duong.tokyolife.Model.ObjectClass.LoaiSanPham;
import com.duong.tokyolife.Model.DangNhap_DangKy.DangNhapModel;
import com.duong.tokyolife.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.duong.tokyolife.Presenter.TrangChu.MenuLeft.PresenterLogicMenuLeftTrangChu;
import com.duong.tokyolife.Presenter.TrangChu.OptionMenuRight.PresenterLogicOptionMenuFB;
import com.duong.tokyolife.R;
import com.duong.tokyolife.Utils.Data;
import com.duong.tokyolife.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.duong.tokyolife.View.DangNhap_DangKy.DangNhap_DangKyActivity;
import com.duong.tokyolife.View.GioHang.GioHangActivity;
import com.duong.tokyolife.View.HienThiSPTheoLoaiSP.HienThiSPTheoLoaiSPActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IViewTrangChu, GoogleApiClient.OnConnectionFailedListener {
    //menu trai
    Toolbar toolbar;
    TabLayout tabLayout;
    //tab
    ViewPager viewPager;
    //menu phai
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    //menu trái
    ListView listView;
    MenuLoaiSanPhamAdapter menuLoaiSanPhamAdapter;
    List<LoaiSanPham> listMenuTrai;

    PresenterLogicMenuLeftTrangChu presenterLogicMenuLeftTrangChu;

    String tenNguoiDung=null;
    PresenterLogicOptionMenuFB presenterLogicOptionMenuFB;
    AccessToken accessTokenFacebook;

    //Lưu lại menu để hiển thị
    Menu menu;
    MenuItem menuItemDangNhap,menuItemDangXuat;

    DangNhapModel dangNhapModel;
    GoogleApiClient googleApiClient;
    GoogleSignInResult googleSignInResult;

    String cacheDatabase;

    TextView txt_giohangsl;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {

        toolbar=findViewById(R.id.toolbarTrangChu);
        tabLayout=findViewById(R.id.tabsTrangChu);
        viewPager=findViewById(R.id.viewPagerTrangChu);
        drawerLayout=findViewById(R.id.drawerLayout);
        listView=findViewById(R.id.lvMenuTrangChu);

        //hien thi menu phai
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //hien thi toolbar
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();

        //hienthi viewpager
        ViewPagerTrangChuAdapter viewPagerTrangChuAdapter = new ViewPagerTrangChuAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerTrangChuAdapter);
        viewPagerTrangChuAdapter.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);

        //set Adapter menu trái
        listMenuTrai = new ArrayList<>();
        presenterLogicMenuLeftTrangChu = new PresenterLogicMenuLeftTrangChu(this);
        presenterLogicMenuLeftTrangChu.layDanhSachLoaiSP();
        menuLoaiSanPhamAdapter = new MenuLoaiSanPhamAdapter(this,R.layout.custom_item_loaisanpham_menu_left,listMenuTrai);
        listView.setAdapter(menuLoaiSanPhamAdapter);

        //Khai bao model de lay google thong tin tu google sign in result
        dangNhapModel = new DangNhapModel();
        googleApiClient = dangNhapModel.layGoogleAPIClient(this,this);

    }

    private void addEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,"Bạn chọn loại sp có mã: "+listMenuTrai.get(position).getTenLoaiSP(),Toast.LENGTH_SHORT).show();
                Intent iSanPham = new Intent(MainActivity.this, HienThiSPTheoLoaiSPActivity.class);
                iSanPham.putExtra("maloaisp",listMenuTrai.get(position).getMaLoaiSP());
                startActivity(iSanPham);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phai_trangchu,menu);
        this.menu=menu;

        menuItemDangNhap=menu.findItem(R.id.itDangNhap);
        menuItemDangXuat=menu.findItem(R.id.itLogout);
        //Lay du lieu ng dung google
        googleSignInResult = dangNhapModel.layThongTinDangNhapGG(googleApiClient);
        //Lấy tên người dùng FB
        presenterLogicOptionMenuFB = new PresenterLogicOptionMenuFB();
        accessTokenFacebook=presenterLogicOptionMenuFB.layAccesTokenFacebook();
        //lay cache database
        cacheDatabase = dangNhapModel.layCacheDangNhapDatabase(this);

        //facebook
        if (accessTokenFacebook!=null){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessTokenFacebook, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tenNguoiDung = object.getString("name");
                        Data.code=accessTokenFacebook.getUserId();
                        Data.name=tenNguoiDung;
                        menuItemDangNhap.setTitle(tenNguoiDung);
//                    Log.d("tenNgDungFB",tenNguoiDung);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle bundle = new Bundle();
            bundle.putString("fields","name");
            graphRequest.setParameters(bundle);
            graphRequest.executeAsync();
        }
        //google
        if (googleSignInResult!=null){
            Data.code=googleSignInResult.getSignInAccount().getId();
            Data.name=googleSignInResult.getSignInAccount().getDisplayName();
            menuItemDangNhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }
        //database
        if (!cacheDatabase.equals("")){
            menuItemDangNhap.setTitle(cacheDatabase);
        }

        // hien item dang xuat
        if (accessTokenFacebook!=null || googleSignInResult!=null || !cacheDatabase.equals("")){
            menuItemDangXuat.setVisible(true);
        }

        MenuItem itemGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itemGioHang);
        txt_giohangsl = giaoDienCustomGioHang.findViewById(R.id.txtGioHangSL);
        txt_giohangsl.setText(String.valueOf(presenterLogicOptionMenuFB.soLuongSanPhamTrongGioHang(MainActivity.this)));

        txt_giohangsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        int idMenu = item.getItemId();
        switch (idMenu){
            case R.id.itDangNhap:
                if (accessTokenFacebook==null && googleSignInResult==null && cacheDatabase.equals("")){
                    Intent iDangNhap = new Intent(MainActivity.this, DangNhap_DangKyActivity.class);
                    startActivity(iDangNhap);
                } break;
            case R.id.itLogout:
                if (accessTokenFacebook!=null){
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }
                if (googleSignInResult!=null){
                    Auth.GoogleSignInApi.signOut(googleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }

                if (!cacheDatabase.equals("")){
                    dangNhapModel.updateCacheDangNhapDatabase(this,"");
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }
                Data.code="";
                Data.name="";
                break;
        }
        return true;
    }

    @Override
    public void hienThiDS_MenuLeft(List<LoaiSanPham> list) {
        listMenuTrai=list;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MenuItem itemGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itemGioHang);
        txt_giohangsl = giaoDienCustomGioHang.findViewById(R.id.txtGioHangSL);
        txt_giohangsl.setText(String.valueOf(presenterLogicOptionMenuFB.soLuongSanPhamTrongGioHang(MainActivity.this)));
        txt_giohangsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
    }
}