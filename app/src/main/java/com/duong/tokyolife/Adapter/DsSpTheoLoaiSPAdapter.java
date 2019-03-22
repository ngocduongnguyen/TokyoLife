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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class DsSpTheoLoaiSPAdapter extends RecyclerView.Adapter<DsSpTheoLoaiSPAdapter.SPbyLoaiHolder> {

    Context context;
    List<SanPham> dsSanPham;
    public DsSpTheoLoaiSPAdapter(Context context, List<SanPham> dsSanPham){
        this.context = context;
        this.dsSanPham=dsSanPham;
    }

    public class SPbyLoaiHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtTen,txtGia,txtGiamGia;

        public SPbyLoaiHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_item_sanpham);
            txtTen=itemView.findViewById(R.id.txt_tenSanPham);
            txtGia=itemView.findViewById(R.id.txt_giaSanPham);
            txtGiamGia=itemView.findViewById(R.id.txt_giamGiaSp);
        }
    }

    @NonNull
    @Override
    public SPbyLoaiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_sanpham,viewGroup,false);
        SPbyLoaiHolder sPbyLoaiHolder = new SPbyLoaiHolder(view);
        return sPbyLoaiHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SPbyLoaiHolder sPbyLoaiHolder, int i) {
        SanPham sanPham = dsSanPham.get(i);
        sPbyLoaiHolder.txtTen.setText(sanPham.getTensp());
        Picasso.with(context).load(sanPham.getAnhlon()).resizeDimen(R.dimen._80sdp,R.dimen._100sdp).into(sPbyLoaiHolder.imageView);
        //Định dạng tiền tệ
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String giaSP = numberFormat.format(sanPham.getGia());
        sPbyLoaiHolder.txtGia.setText(giaSP+" VNĐ");
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }
}
