package com.duong.tokyolife.View.TrangChu;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.duong.tokyolife.Adapter.MenuLoaiSanPhamAdapter;
import com.duong.tokyolife.Adapter.ViewPagerTrangChuAdapter;
import com.duong.tokyolife.Model.ObjectClass.LoaiSanPham;
import com.duong.tokyolife.Model.TrangChu.DataJSONMenuLeft;
import com.duong.tokyolife.Presenter.TrangChu.PresenterLogicMenuLeftTrangChu;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.DangNhap_DangKy.DangNhap_DangKyActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IViewTrangChu {
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

    private LruCache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        menuLoaiSanPhamAdapter = new MenuLoaiSanPhamAdapter(this,R.layout.item_loaisanpham_menu_left,listMenuTrai);
        listView.setAdapter(menuLoaiSanPhamAdapter);

    }

    private void addEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Bạn chọn: "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phai_trangchu,menu);
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
                Intent iDangNhap = new Intent(MainActivity.this, DangNhap_DangKyActivity.class);
                startActivity(iDangNhap);
        }

        return true;
    }

    @Override
    public void hienThiDS_MenuLeft(List<LoaiSanPham> list) {
        listMenuTrai=list;
    }
}
