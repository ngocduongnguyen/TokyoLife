package com.duong.tokyolife.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duong.tokyolife.Model.ObjectClass.ChiTietDonHang;
import com.duong.tokyolife.Model.ObjectClass.DonHang;
import com.duong.tokyolife.Model.ObjectClass.HoaDon;
import com.duong.tokyolife.Model.QLDonHang.QLDonHangModel;
import com.duong.tokyolife.Presenter.QLDonHang.PresenterLogicQLDH;
import com.duong.tokyolife.R;
import com.duong.tokyolife.View.QLDonHang.IViewQLDH;

import java.util.List;

public class DanhSachDonHangAdapter extends RecyclerView.Adapter<DanhSachDonHangAdapter.ViewHolder> {

    Context context;
    List<DonHang> ds;
    IViewQLDH iViewQLDH;
    long idkhachhang;
    public DanhSachDonHangAdapter(IViewQLDH iViewQLDH,long idkhachhang, Context context, List<DonHang> ds){
        this.context=context;
        this.ds=ds;
        this.iViewQLDH=iViewQLDH;
        this.idkhachhang=idkhachhang;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mahd,trangthai,tennguoinhan,diachi,giatri,ngaymua,sodienthoai;
        LinearLayout lnThongSoKyThuat;
        Button huyDon;
        int tongtien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mahd = itemView.findViewById(R.id.mahd);
            trangthai = itemView.findViewById(R.id.trangthai);
            tennguoinhan = itemView.findViewById(R.id.tennguoinhan);
            diachi = itemView.findViewById(R.id.diachinhan);
            giatri = itemView.findViewById(R.id.giatridonhang);
            ngaymua = itemView.findViewById(R.id.ngaymua);
            sodienthoai = itemView.findViewById(R.id.sodt);
            huyDon = itemView.findViewById(R.id.huydonhang);
            lnThongSoKyThuat = itemView.findViewById(R.id.lnDanhSachSanPham);
            tongtien = 0;
        }
    }
    @NonNull
    @Override
    public DanhSachDonHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_don_hang,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DanhSachDonHangAdapter.ViewHolder viewHolder, final int i) {
        final DonHang donhang = ds.get(i);
        viewHolder.mahd.setText("Mã hóa đơn: "+String.valueOf(donhang.getMaHD()));
        viewHolder.trangthai.setText("Trạng thái: "+donhang.getTrangthai());
        viewHolder.tennguoinhan.setText("Tên người nhận: "+donhang.getTennguoinhan());
        viewHolder.diachi.setText("Địa chỉ nhận: "+donhang.getDiachi());
        viewHolder.ngaymua.setText("Ngày mua: "+donhang.getNgayMua());
        viewHolder.sodienthoai.setText("Số điện thoại: "+donhang.getSodt());

        List<ChiTietDonHang> chiTietDonHang = donhang.getChiTietDonHang();

        int count = chiTietDonHang.size();

        for (int j = 0; j < count; j++){
            LinearLayout lnChiTiet = new LinearLayout(context);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView ten = new TextView(context);
            ten.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,2));
            ten.setText(chiTietDonHang.get(j).getTensp());
            ten.setTextColor(Color.RED);

            TextView giatri = new TextView(context);
            giatri.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,3));
            giatri.setText("Số lượng:"+chiTietDonHang.get(j).getSoluong());

            TextView giamgia = new TextView(context);
            giamgia.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,3));
            giamgia.setText("Giảm giá:"+chiTietDonHang.get(j).getGiamgia()+"%");

            lnChiTiet.addView(ten);
            lnChiTiet.addView(giatri);
            lnChiTiet.addView(giamgia);

            viewHolder.lnThongSoKyThuat.addView(lnChiTiet);

            viewHolder.tongtien = viewHolder.tongtien + chiTietDonHang.get(j).getGia();

            if (donhang.getTrangthai().equals("Chờ kiểm duyệt")) {
                    viewHolder.huyDon.setVisibility(View.VISIBLE);
                    viewHolder.huyDon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Messenges");
                            builder.setMessage("Bạn có muốn hủy đơn hàng: "+String.valueOf(donhang.getMaHD()));
                            builder.setCancelable(false);

                            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    QLDonHangModel qlDonHangModel = new QLDonHangModel();
                                    qlDonHangModel.huyDonHang(donhang.getMaHD());
                                    Toast.makeText(context,"Đã hủy đơn hàng: "+String.valueOf(donhang.getMaHD()),Toast.LENGTH_SHORT).show();
                                    PresenterLogicQLDH presenterLogicQLDH = new PresenterLogicQLDH(iViewQLDH);
                                    presenterLogicQLDH.layDonHang(idkhachhang);
                                }
                            });
                            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
             });
            }
            viewHolder.giatri.setText("Tổng giá trị đơn hàng: "+viewHolder.tongtien+" VND");
        }
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }
}
