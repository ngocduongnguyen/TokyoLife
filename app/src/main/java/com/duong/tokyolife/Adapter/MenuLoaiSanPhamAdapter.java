package com.duong.tokyolife.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.duong.tokyolife.Model.ObjectClass.LoaiSanPham;
import com.duong.tokyolife.R;

import java.util.List;

public class MenuLoaiSanPhamAdapter extends ArrayAdapter<LoaiSanPham> {
    Activity context;
    int resource;
    List<LoaiSanPham> objects;
    public MenuLoaiSanPhamAdapter(Activity context, int resource, List<LoaiSanPham> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_item_loaisanpham_menu_left,parent,false);

        TextView textView = view.findViewById(R.id.tvTitleMenuTraiTrangChu);
        textView.setText(objects.get(position).getTenLoaiSP());

        return view;
    }
}
