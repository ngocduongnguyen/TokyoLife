package com.duong.tokyolife.Utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class LoadMore extends RecyclerView.OnScrollListener {

    int itemAnDauTien = 0;
    int tongItem = 0;
    int itemLoadTruoc = 3;

    RecyclerView.LayoutManager layoutManager;

    public LoadMore(RecyclerView.LayoutManager layoutManager){
        this.layoutManager=layoutManager;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongItem = layoutManager.getItemCount();

        if (layoutManager instanceof LinearLayoutManager){
            itemAnDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager){
            itemAnDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        Log.d("kiemtraloadmore", String.valueOf(tongItem)+"----"+itemAnDauTien);
    }
}
