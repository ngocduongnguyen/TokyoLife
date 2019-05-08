package com.duong.tokyolife.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.duong.tokyolife.View.TrangChu.Fragment.FragmentCTKM;
import com.duong.tokyolife.View.TrangChu.Fragment.FragmentNoiBat;
import com.duong.tokyolife.View.TrangChu.Fragment.FragmentSanPhamMoi;
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
                FragmentSanPhamMoi fragmentSanPhamMoi = new FragmentSanPhamMoi();
                return fragmentSanPhamMoi;

            case 2:
                FragmentCTKM fragmentCTKM = new FragmentCTKM();
                return fragmentCTKM;

            case 3:
                FragmentTinTuc fragmentTinTuc = new FragmentTinTuc();
                return fragmentTinTuc;

            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Nổi bật";

            case 1:
                return "Sản phẩm mới";

            case 2:
                return "Chương trình khuyến mại";

            case 3:
                return "Tin tức";

            default:return null;
        }
    }
}
