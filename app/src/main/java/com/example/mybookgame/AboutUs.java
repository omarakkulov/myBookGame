package com.example.mybookgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {
    //кнопки на экране
    private Button vk, instagram, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        //обработка нажатий и нахождение кнопок
        vk = findViewById(R.id.vk);
        vk.setOnClickListener(this);

        instagram = findViewById(R.id.instagram);
        instagram.setOnClickListener(this);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.vk:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/omar_akkulov"));
                startActivity(intent);
                break;
            case R.id.instagram:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/o.akkk1/"));
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}