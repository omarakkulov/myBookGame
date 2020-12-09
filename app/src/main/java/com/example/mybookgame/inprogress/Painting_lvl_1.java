//package com.example.mybookgame;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.TextView;
//
//public class Painting_lvl_1 extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
//    private TextView square, circle, triangle, paintInfo;
//    float x, y;
//    String sDown, sMove, sUp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.painting_lvl_1);
//
//        square = findViewById(R.id.square);
//        square.setOnClickListener(this);
//
//        triangle = findViewById(R.id.triangle);
//        triangle.setOnClickListener(this);
//
//        circle = findViewById(R.id.circle);
//        circle.setOnClickListener(this);
//
////        paintInfo = findViewById(R.id.paintInfo);
////        paintInfo.setOnTouchListener(this);
//        paintInfo = findViewById(R.id.paintInfo);
//        paintInfo.setOnTouchListener(this);
//        setContentView(paintInfo);
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        x = event.getX();
//        y = event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                sDown = "Вы нажали на экран. Координаты: x = " + x + ", y = " + y;
//                sMove = "";
//                sUp = "";
//                break;
//            case MotionEvent.ACTION_MOVE:
//                sMove = "Вы ведете по экрану. Координаты: x = " + x + ", y = " + y;
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                sMove = "";
//                sUp = "Вы отключились от экрана. Координаты: x = " + x + ", y = " + y;
//                break;
//        }
//        paintInfo.setText(sDown + "\n" + sMove + "\n" + sUp);
//        return true;
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.square:
//                break;
//            case R.id.circle:
//                break;
//            case R.id.triangle:
//                break;
//        }
//    }
//}