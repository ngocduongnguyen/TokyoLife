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

public class DsSPTheoThuongHieuAdapter extends RecyclerView.Adapter<DsSPTheoThuongHieuAdapter.DsSpbyTH> {

    Context context;
    List<SanPham> dsSanPham;
    public DsSPTheoThuongHieuAdapter(Context context, List<SanPham> dsSanPham){
        this.context=context;
        this.dsSanPham=dsSanPham;
    }

    public class DsSpbyTH extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtTen,txtGia,txtGiamGia;

        public DsSpbyTH(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_item_sanpham);
            txtTen=itemView.findViewById(R.id.txt_tenSanPham);
            txtGia=itemView.findViewById(R.id.txt_giaSanPham);
            txtGiamGia=itemView.findViewById(R.id.txt_giamGiaSp);
        }
    }

    @NonNull
    @Override
    public DsSpbyTH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_sanpham,viewGroup,false);
        DsSpbyTH dsSpbyTH = new DsSpbyTH(view);
        return dsSpbyTH;
    }

    @Override
    public void onBindViewHolder(@NonNull DsSpbyTH dsSpbyTH, int i) {
        SanPham sanPham = dsSanPham.get(i);
        dsSpbyTH.txtTen.setText(sanPham.getTensp());
        Picasso.with(context).load(sanPham.getAnhlon()).resizeDimen(R.dimen._80sdp,R.dimen._100sdp).into(dsSpbyTH.imageView);
        //Định dạng tiền tệ
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String giaSP = numberFormat.format(sanPham.getGia());
        dsSpbyTH.txtGia.setText(giaSP+" VNĐ");
    }

    @Override
    public int getItemCount() {
        return dsSanPham.size();
    }
}
