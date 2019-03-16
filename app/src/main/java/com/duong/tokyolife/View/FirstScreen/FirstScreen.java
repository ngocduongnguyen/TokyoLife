package com.duong.tokyolife.View.FirstScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.duong.tokyolife.R;
import com.duong.tokyolife.View.TrangChu.MainActivity;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent iTrangChu = new Intent(FirstScreen.this, MainActivity.class);
                    startActivity(iTrangChu);
                }
            }
        });
        thread.start();
    }
}
