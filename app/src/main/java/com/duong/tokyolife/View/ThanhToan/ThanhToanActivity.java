package com.duong.tokyolife.View.ThanhToan;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duong.tokyolife.Model.ObjectClass.ChiTietHoaDon;
import com.duong.tokyolife.Model.ObjectClass.ChiTietSanPham;
import com.duong.tokyolife.Model.ObjectClass.HoaDon;
import com.duong.tokyolife.Model.ObjectClass.SanPham;
import com.duong.tokyolife.Model.ThanhToan.ThanhToanModel;
import com.duong.tokyolife.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.duong.tokyolife.R;
import com.duong.tokyolife.Utils.Data;
import com.duong.tokyolife.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.duong.tokyolife.View.DangNhap_DangKy.DangNhap_DangKyActivity;
import com.duong.tokyolife.View.TrangChu.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements IViewThanhToan{

    TextInputLayout ipTenNguoiNhan, ipSodienthoai, ipDiachi;
    EditText edTenNguoiNhan, edSodienthoai, edDiachi;
    CheckBox checkBox;
    Button btnThanhToan;
    ImageView btnCOD,btnNoneCOD;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons;
    TextView txtCod,txtNoneCod;
    ImageView btnHome;

    int chuyenkhoan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        addControls();
        addEvents();
    }
    private void addControls(){
        ipTenNguoiNhan=findViewById(R.id.ipTenNguoiNhan);
        ipSodienthoai=findViewById(R.id.ipSoDienThoai);
        ipDiachi=findViewById(R.id.ipDiaChiGiaoHang);
        edTenNguoiNhan = findViewById(R.id.edTenNguoiNhan);
        edSodienthoai = findViewById(R.id.edSoDienThoai);
        edDiachi = findViewById(R.id.edDiaChiGiaoHang);
        checkBox = findViewById(R.id.cbCamKet);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        txtCod = findViewById(R.id.txtCod);
        txtNoneCod = findViewById(R.id.txtNoneCod);
        btnCOD = findViewById(R.id.btnCod);
        btnNoneCOD = findViewById(R.id.btnNoneCOD);
        btnHome = findViewById(R.id.btnHome);

        chiTietHoaDons = new ArrayList<>();
        presenterLogicThanhToan = new PresenterLogicThanhToan(this,getApplicationContext());
    }

    private void addEvents(){
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Data.code.equals("")){
                    Toast.makeText(ThanhToanActivity.this,"Bạn phải đăng nhập để mua hàng!",Toast.LENGTH_SHORT).show();
                    Intent iDangNhap = new Intent(ThanhToanActivity.this, DangNhap_DangKyActivity.class);
                    startActivity(iDangNhap);
                }else {
                    boolean kiemtra = false;
                    String tenNguoiNhan = edTenNguoiNhan.getText().toString().trim();
                    String soDienThoai = edSodienthoai.getText().toString().trim();
                    String diaChi = edDiachi.getText().toString().trim();
                    if (tenNguoiNhan.equals("")){
                        Toast.makeText(getApplicationContext(),"Cần nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                        kiemtra = false;
                    } else{
                        kiemtra = true;
                    }

                    if (soDienThoai.equals("")){
                        Toast.makeText(getApplicationContext(),"Cần nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                        kiemtra = false;
                    } else{
                        kiemtra = true;
                    }

                    if (diaChi.equals("")){
                        Toast.makeText(getApplicationContext(),"Cần nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                        kiemtra = false;
                    } else{
                        kiemtra = true;
                    }

                    if (kiemtra){
                        if (checkBox.isChecked()){
                            //code thêm
                            HoaDon hoaDon = new HoaDon();
                            hoaDon.setTenNguoiNhan(tenNguoiNhan);
                            hoaDon.setDiaChi(diaChi);
                            hoaDon.setSoDT(soDienThoai);
                            hoaDon.setChuyenKhoan(chuyenkhoan);
                            hoaDon.setListChiTiet(chiTietHoaDons);

                            presenterLogicThanhToan.layDanhSachSanPhamtrongGioHang();
                            presenterLogicThanhToan.themHoaDon(hoaDon);
                        }else {
                            Toast.makeText(getApplicationContext(),"Bạn cần đảm bảo thông tin bạn nhập là chính xác!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnCOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChuyenKhoan(txtCod,txtNoneCod);
                chuyenkhoan = 0;
            }
        });

        btnNoneCOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChuyenKhoan(txtNoneCod,txtCod);
                chuyenkhoan = 1;
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void datHangThanhCong() {
        Toast.makeText(getApplicationContext(),"Chấp nhận đơn hàng!",Toast.LENGTH_SHORT).show();
        Intent iTrangChu = new Intent(ThanhToanActivity.this, MainActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void datHangThatBai() {
        Toast.makeText(getApplicationContext(),"Đơn hàng bị từ chối!",Toast.LENGTH_SHORT).show();
        Intent iTrangChu = new Intent(ThanhToanActivity.this, MainActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void layDanhSachSanPhamTrongGioHang(List<SanPham> dsSanPham) {
        for (int i=0;i<dsSanPham.size();i++) {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSp(dsSanPham.get(i).getMasp());
            chiTietHoaDon.setSoLuong(dsSanPham.get(i).getSoluong());
            chiTietHoaDon.setGiamGia(dsSanPham.get(i).getGiamgia());
            chiTietHoaDons.add(chiTietHoaDon);
        }
    }

    private void clickChuyenKhoan(TextView txtClick, TextView txtNoneClick){
        txtClick.setTextColor(getResources().getColor(R.color.colorBlue));
        txtNoneClick.setTextColor(getResources().getColor(R.color.colorBlack));
    }
}
