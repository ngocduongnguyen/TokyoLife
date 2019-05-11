package com.duong.tokyolife.View.ChiTietBaiViet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.duong.tokyolife.Model.ObjectClass.BaiViet;
import com.duong.tokyolife.Presenter.ChiTietBaiViet.PresenterLogicChiTietBaiViet;
import com.duong.tokyolife.R;

public class ChiTietBaiVietActivity extends AppCompatActivity implements IViewChiTietBaiViet{

    int matintuc;
    TextView title, des;
    WebView webView;
    ImageView iconBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bai_viet);

        Intent intent = getIntent();
        matintuc = intent.getIntExtra("matintuc",0);

        title = findViewById(R.id.title);
        des = findViewById(R.id.des);

        iconBack = findViewById(R.id.iconBack);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView = findViewById(R.id.wvBaiViet);

        PresenterLogicChiTietBaiViet presenterLogicChiTietBaiViet = new PresenterLogicChiTietBaiViet(this);
        presenterLogicChiTietBaiViet.layChihTietBaiViet(matintuc);

    }

    @Override
    public void hienThiChiTietBaiViet(BaiViet baiViet) {
        title.setText(baiViet.getTieuDe());
        des.setText(Html.fromHtml(baiViet.getMoTa()));
        String data = "<html><head></head><body>"+baiViet.getNoiDung()+"</body></html>";
        webView.loadData(data,"text/html; charset=utf-8","utf-8");
    }
}
