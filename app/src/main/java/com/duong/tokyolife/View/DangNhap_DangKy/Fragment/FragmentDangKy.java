package com.duong.tokyolife.View.DangNhap_DangKy.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.duong.tokyolife.Model.ObjectClass.NhanVien;
import com.duong.tokyolife.Presenter.DangNhap_DangKy.PresenterLogicDangKy;
import com.duong.tokyolife.R;

public class FragmentDangKy extends Fragment implements IViewDangKy{
    View view;
    Button btnDangKy;

    EditText edHoTen,edEmail,edMK,edNhapLaiMK;
    TextInputLayout ip_edHoten,ip_edEmail,ip_edMK,ip_edNhapLaiMK;

    PresenterLogicDangKy presenterLogicDangKy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dangky, container, false);

        addControls();
        addEvents();

        return view;
    }

    private void addControls() {
        btnDangKy=view.findViewById(R.id.btnDangKy);
        edHoTen = view.findViewById(R.id.edHoTenDK);
        edEmail = view.findViewById(R.id.edEmailDK);
        edMK = view.findViewById(R.id.edPassDK);
        edNhapLaiMK = view.findViewById(R.id.edPassNhaplaiDK);
        ip_edHoten = view.findViewById(R.id.input_edHotenDK);
        ip_edEmail = view.findViewById(R.id.input_edEmailDK);
        ip_edMK = view.findViewById(R.id.input_edMKDK);
        ip_edNhapLaiMK = view.findViewById(R.id.input_edNhapLaiMKDK);

        presenterLogicDangKy = new PresenterLogicDangKy(this);
    }

    private void addEvents() {
        btnDangKy.setOnClickListener(clickDangKy);
    }

    private View.OnClickListener clickDangKy = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String hoTen = edHoTen.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String matkhau = edMK.getText().toString().trim();
            String nhaplaimk = edNhapLaiMK.getText().toString().trim();

            boolean kiemtra = true;

            if (hoTen.equals("")){
                kiemtra=false;
                ip_edHoten.setErrorEnabled(true);
                ip_edHoten.setError("Họ tên không được để trống!");
            } else{
                ip_edHoten.setErrorEnabled(false);
            }
            if (email.equals("")){
                kiemtra=false;
                ip_edEmail.setErrorEnabled(true);
                ip_edEmail.setError("Email không được để trống!");
            } else if(!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
                kiemtra=false;
                ip_edEmail.setErrorEnabled(true);
                ip_edEmail.setError("Email không đúng định dạng!");
            } else{
                ip_edEmail.setErrorEnabled(false);
            }
            if (matkhau.equals("")){
                kiemtra=false;
                ip_edMK.setErrorEnabled(true);
                ip_edMK.setError("Mật khẩu không được để trống!");
            } else{
                ip_edMK.setErrorEnabled(false);
            }
            if (!matkhau.equals(nhaplaimk)){
                kiemtra=false;
                ip_edNhapLaiMK.setErrorEnabled(true);
                ip_edNhapLaiMK.setError("Mật khẩu không trùng khớp!");
            } else{
                ip_edNhapLaiMK.setErrorEnabled(false);
            }

            if (kiemtra){

                NhanVien nhanVien = new NhanVien();
                nhanVien.setTennv(hoTen);
                nhanVien.setTendn(email);
                nhanVien.setMatkhau(matkhau);

                presenterLogicDangKy.dangKyThanhVien(nhanVien);
            }

        }
    };

    @Override
    public void dangKyThanhCong() {
        Toast.makeText(getContext(),"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dangKyThatBai() {
        Toast.makeText(getContext(),"Đăng ký thất bại!",Toast.LENGTH_SHORT).show();
    }
}