package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.ChiTietSanPham.ChiTietSanPhamActivity;
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
        CardView cardView;

        public SPbyLoaiHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardViewSanPham);
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
        final SanPham sanPham = dsSanPham.get(i);
        sPbyLoaiHolder.txtTen.setText(sanPham.getTensp());
        Picasso.with(context).load(sanPham.getAnhlon()).resizeDimen(R.dimen._80sdp,R.dimen._100sdp).into(sPbyLoaiHolder.imageView);
        //Định dạng tiền tệ
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String giaSP = numberFormat.format(sanPham.getGia());
        sPbyLoaiHolder.txtGia.setText(giaSP+" VNĐ");
        sPbyLoaiHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Ma san pham: "+sanPham.getMasp(),Toast.LENGTH_SHORT).show();
                Intent iChiTiet = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTiet.putExtra("masp",sanPham.getMasp());
                context.startActivity(iChiTiet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }
}
