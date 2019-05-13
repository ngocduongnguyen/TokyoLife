package com.duong.tokyolife.View.TaiKhoan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.duong.tokyolife.Model.ObjectClass.NhanVien;
import com.duong.tokyolife.Model.TaiKhoan.TaiKhoanModel;
import com.duong.tokyolife.Presenter.TaiKhoan.PresenterLogicTaiKhoan;
import com.duong.tokyolife.R;
import com.duong.tokyolife.Utils.Data;

public class ThongTinTaiKhoanActivity extends AppCompatActivity implements IViewThongtinTaiKhoan{

    NhanVien nhanVien;
    long idkhachhang;

    EditText manv,tennv,tendn,matkhau,ngaysinh,diachi,sodt,cmnd;
    Spinner gioitinh;
    LinearLayout llTenDangNhap, llMatKhau;
    Button update;
    ImageView btnHome;

    PresenterLogicTaiKhoan presenterLogicTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tai_khoan);

//        idkhachhang = getIntent().getLongExtra("idkhachhang",1);
        update = findViewById(R.id.btnUpdate);
        btnHome = findViewById(R.id.iconBack);
        manv = findViewById(R.id.idTaiKhoan);
        tennv = findViewById(R.id.tenKhachHang);
        tendn = findViewById(R.id.tenDangNhap);
        matkhau = findViewById(R.id.matKhau);
        ngaysinh = findViewById(R.id.ngaySinh);
        diachi = findViewById(R.id.diaChi);
        sodt = findViewById(R.id.soDienThoai);
        gioitinh = findViewById(R.id.gioiTinh);
        cmnd = findViewById(R.id.cccd);
        llTenDangNhap = findViewById(R.id.llTenDangNhap);
        llMatKhau = findViewById(R.id.llMatKhau);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTK();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThongTinTaiKhoanActivity.this,R.layout.support_simple_spinner_dropdown_item,new String[]{"Nam","Nữ"});
        gioitinh.setAdapter(adapter);

        presenterLogicTaiKhoan = new PresenterLogicTaiKhoan(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("idkhachhang");
        if (id.equals(""))
        {
            Toast.makeText(ThongTinTaiKhoanActivity.this,"Bạn chưa đăng nhập!",Toast.LENGTH_SHORT).show();
        }else {
            idkhachhang = Long.parseLong(id);
            presenterLogicTaiKhoan.layThongTintaiKhoan(idkhachhang);
        }
    }

    private void updateTK() {
        String manv,tennv,tendn,matkhau,ngaysinh,diachi,sodt,gioitinh = null,cmnd;
        manv = String.valueOf(this.manv.getText());
        tennv = String.valueOf(this.tennv.getText());
        tendn = String.valueOf(this.tendn.getText());
        matkhau = String.valueOf(this.matkhau.getText());
        ngaysinh = String.valueOf(this.ngaysinh.getText());
        diachi = String.valueOf(this.diachi.getText());
        sodt = String.valueOf(this.sodt.getText());

        NhanVien nv = new NhanVien();

        if (this.gioitinh.getSelectedItem().equals("Nam")) gioitinh = "1";
        else if (this.gioitinh.getSelectedItem().equals("Nữ"))gioitinh = "0";
        cmnd = String.valueOf(this.cmnd.getText());

        nv.setManv(Long.parseLong(manv));
        nv.setTendn(tendn);
        nv.setTennv(tennv);
        nv.setMatkhau(matkhau);
        nv.setNgaysinh(ngaysinh);
        nv.setDiachi(diachi);
        nv.setSodt(sodt);
        nv.setGioitinh(Integer.parseInt(gioitinh));
        nv.setCmnd(cmnd);

        if (nhanVien.getTendn().equals("")&&nhanVien.getMatkhau().equals("")){
            if (manv.equals("")|tennv.equals("")|ngaysinh.equals("")|diachi.equals("")|sodt.equals("")|gioitinh.equals("")|cmnd.equals(""))
                Toast.makeText(ThongTinTaiKhoanActivity.this,"Chưa đủ dữ liệu cập nhật!", Toast.LENGTH_SHORT).show();
            else{
                presenterLogicTaiKhoan.updateTaiKhoan(nv);
                Toast.makeText(ThongTinTaiKhoanActivity.this,"Cập nhật thành công!!", Toast.LENGTH_SHORT).show();
                presenterLogicTaiKhoan.layThongTintaiKhoan(nv.getManv());
            }
        }else {
            if (manv.equals("")|tennv.equals("")|ngaysinh.equals("")|diachi.equals("")|sodt.equals("")|gioitinh.equals("")|cmnd.equals(""))
                Toast.makeText(ThongTinTaiKhoanActivity.this,"Chưa đủ dữ liệu cập nhật!", Toast.LENGTH_SHORT).show();
            else{
                presenterLogicTaiKhoan.updateTaiKhoan(nv);
                Toast.makeText(ThongTinTaiKhoanActivity.this,"Cập nhật thành công!!", Toast.LENGTH_SHORT).show();
                presenterLogicTaiKhoan.layThongTintaiKhoan(nv.getManv());
            }
        }
    }

    @Override
    public void hienThiThongTin(NhanVien nv) {
        nhanVien=nv;
        manv.setText(String.valueOf(nhanVien.getManv()));
        tennv.setText(nhanVien.getTennv());

        if (String.valueOf(nhanVien.getTendn()).equals("null"))
            llTenDangNhap.setVisibility(View.GONE);
        else
            tendn.setText(nhanVien.getTendn());

        if (String.valueOf(nhanVien.getMatkhau()).equals("null"))
            llMatKhau.setVisibility(View.GONE);
        else
            matkhau.setText(nhanVien.getMatkhau());

        if (String.valueOf(nhanVien.getNgaysinh()).equals("null"))
            ngaysinh.setHint("Giá trị rỗng");
        else
            ngaysinh.setText(nhanVien.getNgaysinh());

        if (String.valueOf(nhanVien.getDiachi()).equals("null"))
            diachi.setHint("Giá trị rỗng");
        else
            diachi.setText(nhanVien.getDiachi());

        if (String.valueOf(nhanVien.getSodt()).equals("null"))
            sodt.setHint("Giá trị rỗng");
        else
            sodt.setText(nhanVien.getSodt());

        if (String.valueOf(nhanVien.getNgaysinh()).equals("null"))
            ngaysinh.setHint("Giá trị rỗng, dd-mm-yyyy");
        else
            ngaysinh.setText(nhanVien.getNgaysinh());


        if (nhanVien.getGioitinh() == 1){
            gioitinh.setSelection(0);
            gioitinh.setSelected(true);
        }else if (nhanVien.getGioitinh() == 0){
            gioitinh.setSelection(1);
            gioitinh.setSelected(true);
        } else gioitinh.setSelected(false);

        if (String.valueOf(nhanVien.getCmnd()).equals("null"))
            cmnd.setHint("Giá trị rỗng");
        else
            cmnd.setText(nhanVien.getCmnd());
    }

    @Override
    public void thongBaoThanhCong() {
        Toast.makeText(ThongTinTaiKhoanActivity.this,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void thongBaoThatBai() {
        Toast.makeText(ThongTinTaiKhoanActivity.this,"Cập nhật thất bại!",Toast.LENGTH_SHORT).show();
    }
}
