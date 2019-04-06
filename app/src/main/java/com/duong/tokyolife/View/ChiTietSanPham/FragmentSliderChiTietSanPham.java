package com.duong.tokyolife.View.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duong.tokyolife.R;
import com.squareup.picasso.Picasso;

public class FragmentSliderChiTietSanPham extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider_chi_tiet_san_pham,container,false);

        Bundle bundle = getArguments();
        String linkHinh = bundle.getString("linkHinh");
        ImageView imageView = view.findViewById(R.id.img_slider_chitiet);
        Picasso.with(getContext()).load(linkHinh).into(imageView);

        return view;
    }
}
