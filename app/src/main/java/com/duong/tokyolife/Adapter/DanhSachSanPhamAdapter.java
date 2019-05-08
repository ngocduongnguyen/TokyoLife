package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class DanhSachSanPhamAdapter extends RecyclerView.Adapter<DanhSachSanPhamAdapter.SanPhamViewHolder> {

    Context context;
    List<SanPham> dsSanPhamNoiBat;
    public DanhSachSanPhamAdapter(Context context, List<SanPham> dsSanPhamNoiBat){
        this.context=context;
        this.dsSanPhamNoiBat=dsSanPhamNoiBat;
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTen,txtGia,txtGiamGia;
        CardView cardView;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_item_sanpham);
            txtTen=itemView.findViewById(R.id.txt_tenSanPham);
            txtGia=itemView.findViewById(R.id.txt_giaSanPham);
            txtGiamGia=itemView.findViewById(R.id.txt_giamGiaSp);
            cardView = itemView.findViewById(R.id.cardViewSanPham);
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

        final SanPham sanPham = dsSanPhamNoiBat.get(i);

        sanPhamViewHolder.txtTen.setText(sanPham.getTensp());
        Picasso.with(context).load(sanPham.getAnhlon()).resizeDimen(R.dimen._140sdp,R.dimen._140sdp).into(sanPhamViewHolder.imageView);
        //Định dạng tiền tệ
//        NumberFormat numberFormat = new DecimalFormat("###,###");
//        String giaSP = numberFormat.format(sanPham.getGia());
//        sanPhamViewHolder.txtGia.setText(giaSP+" VNĐ");


        int giaGoc = sanPham.getGia();
        if (sanPham.getGiamgia()!=0){
            int giakm = 0;
            int phantramkm = sanPham.getGiamgia();
            giakm = giaGoc-giaGoc*phantramkm/100;
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String giaSP = numberFormat.format(giakm);
            sanPhamViewHolder.txtGia.setText(giaSP+" VNĐ");

            String giaSPkm = numberFormat.format(giaGoc);
            sanPhamViewHolder.txtGiamGia.setVisibility(View.VISIBLE);
            sanPhamViewHolder.txtGiamGia.setText(giaSPkm+" VNĐ");
            sanPhamViewHolder.txtGiamGia.setPaintFlags(sanPhamViewHolder.txtGiamGia.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String giaSP = numberFormat.format(giaGoc);
            sanPhamViewHolder.txtGia.setText(giaSP+" VNĐ");
        }

        sanPhamViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTiet = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTiet.putExtra("masp",sanPham.getMasp());
                context.startActivity(iChiTiet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsSanPhamNoiBat.size();
    }

}