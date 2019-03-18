package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duong.tokyolife.Model.ObjectClass.NoiBat;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Model.ObjectClass.ThuongHieu;
import com.duong.tokyolife.R;

import java.util.List;

public class NoiBatAdapter extends RecyclerView.Adapter<NoiBatAdapter.ViewHolder> {
    Context context;
    List<NoiBat> noiBatList;
    public NoiBatAdapter(Context context, List<NoiBat> noiBatList){
        this.context=context;
        this.noiBatList=noiBatList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerThuongHieu, recyclerSanPham;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerThuongHieu=itemView.findViewById(R.id.recyclerNoiBatThuongHieu);
            recyclerSanPham=itemView.findViewById(R.id.recyclerNoiBatSanPham);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_view_noibat,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NoiBat noiBat = noiBatList.get(i);
        //Hien thi danh sach thuong hieu
        List<ThuongHieu> dsThuongHieu = noiBat.getListThuongHieuNoiBat();
        ThuongHieuNoiBatAdapter THnoiBatAdapter = new ThuongHieuNoiBatAdapter(context,dsThuongHieu);

        RecyclerView.LayoutManager layoutManagerTH = new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false);
        viewHolder.recyclerThuongHieu.setAdapter(THnoiBatAdapter);
        viewHolder.recyclerThuongHieu.setLayoutManager(layoutManagerTH);
        THnoiBatAdapter.notifyDataSetChanged();

        //Hien thi danh sach san pham
        List<SanPham> dsSanPham = noiBat.getListLoaiSpNoiBat();
        SanPhamNoiBatAdapter sanPhamNoiBatAdapter = new SanPhamNoiBatAdapter(context,dsSanPham);

        RecyclerView.LayoutManager layoutManagerSP = new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false);
        viewHolder.recyclerSanPham.setAdapter(sanPhamNoiBatAdapter);
        viewHolder.recyclerSanPham.setLayoutManager(layoutManagerSP);
        sanPhamNoiBatAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noiBatList.size();
    }

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
}
