package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamNoiBatAdapter extends RecyclerView.Adapter<SanPhamNoiBatAdapter.SanPhamViewHolder> {

    Context context;
    List<SanPham> dsSanPhamNoiBat;
    public SanPhamNoiBatAdapter(Context context, List<SanPham> dsSanPhamNoiBat){
        this.context=context;
        this.dsSanPhamNoiBat=dsSanPhamNoiBat;
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTen,txtGia,txtGiamGia;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_item_sanpham);
            txtTen=itemView.findViewById(R.id.txt_tenSanPham);
            txtGia=itemView.findViewById(R.id.txt_giaSanPham);
            txtGiamGia=itemView.findViewById(R.id.txt_giamGiaSp);
        }
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_sanpham,viewGroup,false);

        SanPhamViewHolder sanPhamViewHolder = new SanPhamViewHolder(view);

        return sanPhamViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder sanPhamViewHolder, int i) {

        SanPham sanPham = dsSanPhamNoiBat.get(i);

        sanPhamViewHolder.txtTen.setText(sanPham.getTensp());
        sanPhamViewHolder.txtGia.setText(String.valueOf(sanPham.getGia()));
        Picasso.with(context).load(sanPham.getAnhlon()).into(sanPhamViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return dsSanPhamNoiBat.size();
    }

}