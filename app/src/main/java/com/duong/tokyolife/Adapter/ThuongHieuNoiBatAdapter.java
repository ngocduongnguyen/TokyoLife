package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duong.tokyolife.View.HienThiSPTheoThuongHieu.HienThiSPTheoThuongHieuActivity;
import com.squareup.picasso.Picasso;

import com.duong.tokyolife.Model.ObjectClass.ThuongHieu;
import com.duong.tokyolife.R;

import java.time.Instant;
import java.util.List;

public class ThuongHieuNoiBatAdapter extends RecyclerView.Adapter<ThuongHieuNoiBatAdapter.ThuongHieuHolder>{
    Context context;
    List<ThuongHieu> listThuongHieu;

    public ThuongHieuNoiBatAdapter(Context context, List<ThuongHieu> listThuongHieu){
        this.context=context;
        this.listThuongHieu=listThuongHieu;
    }



    public class ThuongHieuHolder extends RecyclerView.ViewHolder {
        TextView txtTieuDe;
        ImageView imgThuongHieu;
        LinearLayout linearLayout;

        public ThuongHieuHolder(@NonNull View itemView) {
            super(itemView);
            txtTieuDe=itemView.findViewById(R.id.title_noibat);
            imgThuongHieu=itemView.findViewById(R.id.img_item_noibat);
            linearLayout=itemView.findViewById(R.id.linearItemThuongHieu);
        }
    }

    @NonNull
    @Override
    public ThuongHieuHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_thuonghieu,viewGroup,false);

        ThuongHieuHolder holder = new ThuongHieuHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuongHieuHolder thuongHieuHolder, int i) {
            final ThuongHieu thuongHieu = listThuongHieu.get(i);
            thuongHieuHolder.txtTieuDe.setText(thuongHieu.getTenth());
            Picasso.with(context).load(thuongHieu.getHinhth()).resizeDimen(R.dimen._100sdp,R.dimen._100sdp).into(thuongHieuHolder.imgThuongHieu);
            thuongHieuHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"click: "+thuongHieu.getMath()+" ---Ten: "+thuongHieu.getTenth(),Toast.LENGTH_SHORT).show();
                    Intent iHienThiSpTheoDanhMuc = new Intent(context, HienThiSPTheoThuongHieuActivity.class);
                    iHienThiSpTheoDanhMuc.putExtra("dsspofth",thuongHieu.getMath());
                    context.startActivity(iHienThiSpTheoDanhMuc);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listThuongHieu.size();
    }
}
