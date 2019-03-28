package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.R;

import java.util.List;

public class DsDanhGiaSanPhamAdapter extends RecyclerView.Adapter<DsDanhGiaSanPhamAdapter.ViewHolder> {

    Context context;
    List<DanhGia> dsDanhGia;
    int limit;
    public DsDanhGiaSanPhamAdapter(Context context, List<DanhGia> dsDanhGia, int limit){
        this.context=context;
        this.dsDanhGia=dsDanhGia;
        this.limit=limit;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTite,txtName,txtContent;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTite = itemView.findViewById(R.id.txt_tieude_danhgia);
            txtName = itemView.findViewById(R.id.txtNguoiDanhGia);
            txtContent = itemView.findViewById(R.id.txt_noiDungDanhGia);
            ratingBar = itemView.findViewById(R.id.rbDanhGia);
        }
    }

    @NonNull
    @Override
    public DsDanhGiaSanPhamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_danhgia,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DsDanhGiaSanPhamAdapter.ViewHolder viewHolder, int i) {
        DanhGia danhGia = dsDanhGia.get(i);

        viewHolder.txtTite.setText(danhGia.getTieude());
        viewHolder.txtName.setText("Được đánh giá bởi: "+danhGia.getTenthietbi()+" ; Ngày: "+danhGia.getNgaydanhgia());
        viewHolder.txtContent.setText(danhGia.getNoidung());
        viewHolder.ratingBar.setRating(danhGia.getSosao());
    }

    @Override
    public int getItemCount() {
        int count=0;
        if (dsDanhGia.size()<limit){
            count = dsDanhGia.size();
        }else{
            if (limit!=0){
                count = limit;
            } else {
                count=dsDanhGia.size();
            }
        }
        return count;
    }
}
