package com.duong.tokyolife.View.DanhGia;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.duong.tokyolife.Model.ObjectClass.DanhGia;
import com.duong.tokyolife.Presenter.DanhGia.PresenterLogicDanhGia;
import com.duong.tokyolife.R;
import com.duong.tokyolife.Utils.Data;

public class ThemDanhGiaActivity extends AppCompatActivity implements IViewThemDanhGiaDanhGia {

    TextInputLayout ip_tieude, ip_noidung;
    EditText edTieude, edNoidung;
    Button btnDongY;
    RatingBar ratingBar;

    PresenterLogicDanhGia presenterLogicDanhGia;

    int masp = 0, sosao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);

        addControls();
        addEvents();
    }

    private void addControls() {
        ip_tieude = findViewById(R.id.input_edTieude);
        ip_noidung = findViewById(R.id.input_edNoidung);
        edTieude = findViewById(R.id.edTieuDeDanhGia);
        edNoidung = findViewById(R.id.edNoiDungDanhGia);
        btnDongY = findViewById(R.id.btn_dongy);
        ratingBar = findViewById(R.id.rbDanhGia);
        masp = getIntent().getIntExtra("masp", 0);
    }

    private void addEvents() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                sosao = (int) rating;
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterLogicDanhGia = new PresenterLogicDanhGia(ThemDanhGiaActivity.this);
                DanhGia danhGia = new DanhGia();

//                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//                String madg = telephonyManager.getDeviceId();
//                String madg = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
//                String tenthietbi = Build.MODEL;
                String madg = Data.code;
                String tenthietbi = Data.name;
                String tieude = edTieude.getText().toString().trim();
                String noidung = edNoidung.getText().toString().trim();

                if (tieude.length()>0){
                    ip_tieude.setErrorEnabled(false);
                    ip_tieude.setError("");
                } else {
                    ip_tieude.setErrorEnabled(true);
                    ip_tieude.setError("Tiêu đề rỗng");
                }

                if (noidung.length()>0){
                    ip_noidung.setErrorEnabled(false);
                    ip_noidung.setError("");
                } else {
                    ip_noidung.setErrorEnabled(true);
                    ip_noidung.setError("Nội dung rỗng");
                }

                danhGia.setMadg(madg);
                danhGia.setTenthietbi(tenthietbi);
                danhGia.setMasp(masp);
                danhGia.setTieude(tieude);
                danhGia.setNoidung(noidung);
                danhGia.setSosao(sosao);
                if (!ip_tieude.isErrorEnabled()&&!ip_noidung.isErrorEnabled()){
                    presenterLogicDanhGia.themDanhGia(danhGia);
                    finish();
                }
            }
        });
    }

    @Override
    public void themThanhCong() {
        Toast.makeText(this,"Thêm đánh giá thành công!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void themThatBai() {
        Toast.makeText(this,"Bạn chưa đăng nhập hoặc bạn đã đánh giá sản phẩm này trước đây!",Toast.LENGTH_SHORT).show();
    }
}
