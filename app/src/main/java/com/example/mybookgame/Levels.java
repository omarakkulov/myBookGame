package com.example.mybookgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

// реализуем сразу интерфейс OnItemClickListener для адаптера
public class Levels extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // лист из layout файла levels
    ListView lvLevels;

    final int MAX_STREAMS = 5;
    public SoundPool sp;
    public int soundIdShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        soundIdShot = sp.load(this, R.raw.shot, 1);

        // находим наш лист
        lvLevels = findViewById(R.id.lvLevels);

        //создаем адаптер для него, чтобы вывести все уровни на экран
        // значения из mainLevels, вид из my_list_item
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mainLevels, R.layout.my_list_item);

        // устанавливаем адаптер для нашего листа
        lvLevels.setAdapter(adapter);
        // обработка нажатий на элемент списка
        lvLevels.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if ((int) id >= 0 && id <= 14) {
//            sp.play(soundIdShot, 1, 1, 0, 0, 1);
//        }
        Intent intent;
        switch ((int) id) {
            case 0:
//                intent = new Intent(this, Painting_lvl_1.class);
//                startActivity(intent);
                sp.play(soundIdShot, 1, 1, 0, 0, 1);
                intent = new Intent(view.getContext(), MathGame.class);
                startActivity(intent);
                break;
        }
    }

//    //обрабатываем нажатие на пункты списка
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//        Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//    }
}
