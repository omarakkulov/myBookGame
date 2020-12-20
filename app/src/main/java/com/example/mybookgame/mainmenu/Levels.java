package com.example.mybookgame.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mybookgame.R;
import com.example.mybookgame.mathgame.questions_and_logic.MathGame;

import java.util.Objects;

// реализуем сразу интерфейс OnItemClickListener для адаптера
public class Levels extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // лист из layout файла levels
    ListView lvLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

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
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(view.getContext(), MathGame.class);
                startActivity(intent);
                break;
        }
    }
}
