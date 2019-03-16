package com.duong.tokyolife.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.duong.tokyolife.View.TrangChu.Fragment.FragmentCTKM;
import com.duong.tokyolife.View.TrangChu.Fragment.FragmentNoiBat;
import com.duong.tokyolife.View.TrangChu.Fragment.FragmentTinTuc;

public class ViewPagerTrangChuAdapter extends FragmentPagerAdapter {

    public ViewPagerTrangChuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                FragmentNoiBat fragmentNoiBat = new FragmentNoiBat();
                return fragmentNoiBat;

            case 1:
                FragmentCTKM fragmentCTKM = new FragmentCTKM();
                return fragmentCTKM;

            case 2:
                FragmentTinTuc fragmentTinTuc = new FragmentTinTuc();
                return fragmentTinTuc;

            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Nổi bật";

            case 1:
                return "Chương trình khuyến mại";

            case 2:
                return "Tin tức";

            default:return null;
        }
    }
}
