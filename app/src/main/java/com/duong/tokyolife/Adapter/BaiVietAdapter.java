package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duong.tokyolife.Model.ObjectClass.BaiViet;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.ChiTietBaiViet.ChiTietBaiVietActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BaiVietAdapter extends RecyclerView.Adapter<BaiVietAdapter.ViewHolder> {
    Context context;
    List<BaiViet> ds;

    public BaiVietAdapter(Context context, List<BaiViet> ds){
        this.context=context;
        this.ds=ds;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView title, des;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.itemBaiViet);
            imageView = itemView.findViewById(R.id.imgBgPost);
            title = itemView.findViewById(R.id.tvTieuDeBV);
            des = itemView.findViewById(R.id.tvMoTaBV);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_bai_viet,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final BaiViet baiViet = ds.get(i);
        Picasso.with(context).load(baiViet.getAnhDaiDien()).into(viewHolder.imageView);
        viewHolder.title.setText(baiViet.getTieuDe());
        viewHolder.des.setText(Html.fromHtml(baiViet.getMoTa()));

        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietBaiViet = new Intent(context, ChiTietBaiVietActivity.class);
                iChiTietBaiViet.putExtra("matintuc",baiViet.getMatintuc());
                context.startActivity(iChiTietBaiViet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }


}
