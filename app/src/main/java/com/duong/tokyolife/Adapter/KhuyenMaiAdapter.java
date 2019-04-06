package com.duong.tokyolife.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.duong.tokyolife.Model.ObjectClass.KhuyenMai;
import com.duong.tokyolife.R;
import java.util.List;

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.ViewHolder> {

    Context context;
    List<KhuyenMai> dsKhuyenMai;
    public KhuyenMaiAdapter(Context context, List<KhuyenMai> dsKhuyenMai){
        this.dsKhuyenMai=dsKhuyenMai;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerItemKhuyenMai);
            textView = itemView.findViewById(R.id.txtTieuDeKhuyenMai);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_item_khuyenmai,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        KhuyenMai khuyenMai = dsKhuyenMai.get(i);
        viewHolder.textView.setText(khuyenMai.getTenKm());
        DsSpTheoLoaiSPAdapter dsSpTheoLoaiSPAdapter = new DsSpTheoLoaiSPAdapter(context,khuyenMai.getDsSanPhamKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,3,GridLayoutManager.HORIZONTAL,false);
        viewHolder.recyclerView.setAdapter(dsSpTheoLoaiSPAdapter);
        viewHolder.recyclerView.setLayoutManager(layoutManager);
        dsSpTheoLoaiSPAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dsKhuyenMai.size();
    }
}
