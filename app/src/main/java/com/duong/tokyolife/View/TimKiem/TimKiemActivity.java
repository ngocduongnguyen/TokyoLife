package com.duong.tokyolife.View.TimKiem;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.duong.tokyolife.Adapter.DsSPTheoThuongHieuAdapter;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Presenter.TimKiem.PresenterLogicTimKiem;
import com.duong.tokyolife.R;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements IViewTimKiem, SearchView.OnQueryTextListener {

    Toolbar toolbar;
    RecyclerView recyclerView;

    PresenterLogicTimKiem presenterLogicTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        presenterLogicTimKiem = new PresenterLogicTimKiem(this);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_timkiem);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);

        MenuItem itSearch = menu.findItem(R.id.itTimKiem);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hienthidanhsachtimkiem(List<SanPham> ds) {

        DsSPTheoThuongHieuAdapter dsSPTheoThuongHieuAdapter = new DsSPTheoThuongHieuAdapter(this,ds);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dsSPTheoThuongHieuAdapter);
        dsSPTheoThuongHieuAdapter.notifyDataSetChanged();

    }

    @Override
    public void timkiemthatbai() {
        Toast.makeText(this,"tim kiem that bai!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        presenterLogicTimKiem.laydanhsachtimkiem(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
