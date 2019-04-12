package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duong.tokyolife.Model.GioHang.GioHangModel;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    GioHangModel gioHangModel;
    Context context;
    List<SanPham> ds;
    public GioHangAdapter(Context context, List<SanPham> ds){
        this.context=context;
        this.ds=ds;
        gioHangModel = new GioHangModel();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTieude,txtGia,txtSoluong;
        ImageView imgHinhGioHang;
        Button  btnXoaGioHang,btnCong,btnTru;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                txtTieude = itemView.findViewById(R.id.txtTieudeGiohang);
                txtGia = itemView.findViewById(R.id.txtGiaGiohang);
                txtSoluong = itemView.findViewById(R.id.txtSoLuong);
                imgHinhGioHang = itemView.findViewById(R.id.img_hinh_giohang);
                btnXoaGioHang = itemView.findViewById(R.id.btnXoa);
                btnCong = itemView.findViewById(R.id.btnCong);
                btnTru = itemView.findViewById(R.id.btnTru);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_ds_giohang,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final SanPham sanPham = ds.get(i);
        viewHolder.txtTieude.setText(sanPham.getTensp());

        NumberFormat format = new DecimalFormat("###.###");
        String gia = format.format(sanPham.getGia());
        viewHolder.txtGia.setText(gia+" VND");

        byte[] hinhgiohang = sanPham.getHinhGioHang();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhgiohang,0,hinhgiohang.length);
        viewHolder.imgHinhGioHang.setImageBitmap(bitmap);

        viewHolder.txtSoluong.setText(String.valueOf(sanPham.getSoluong()));

        viewHolder.btnXoaGioHang.setTag(sanPham.getMasp());
        viewHolder.btnXoaGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,"xoa gio hang",Toast.LENGTH_SHORT).show();
                gioHangModel.MoKetNoi(context);
                gioHangModel.xoaSP((Integer) v.getTag());
                ds.remove(i);
                notifyDataSetChanged();
            }
        });

        viewHolder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,"Cộng",Toast.LENGTH_SHORT).show();
                int soluong = Integer.parseInt(viewHolder.txtSoluong.getText().toString());
                int tonkho = sanPham.getSoluongtonkho();

                if (soluong<tonkho){
                    soluong++;
                } else {
                    Toast.makeText(context,"Vượt quá số lượng hàng có trong kho!",Toast.LENGTH_SHORT).show();
                }
                viewHolder.txtSoluong.setText(String.valueOf(soluong));
                gioHangModel.capNhapGioHang(context,sanPham.getMasp(),soluong);
            }
        });

        viewHolder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,"Trừ",Toast.LENGTH_SHORT).show();
                int soluong = Integer.parseInt(viewHolder.txtSoluong.getText().toString());
                if (soluong>1){
                    soluong--;
                }
                viewHolder.txtSoluong.setText(String.valueOf(soluong));
                gioHangModel.capNhapGioHang(context,sanPham.getMasp(),soluong);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }
}
