package com.example.mybookgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // кнопки на главном экране
    private Button startGame, quitGame, settings, aboutUs;

    final int MAX_STREAMS = 5;
    public SoundPool sp;
    public int soundIdShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        turnOnViewElements();

        //Сначала создаем SoundPool. На вход ему передаем:
        //- максимальное кол-во одновременно воспроизводимых файлов
        //- аудио-поток, который будет использоваться
        //- некий параметр качества, который пока что игнорируется системой. Рекомендуется передавать туда 0
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);

        //Далее загружаем файлы. Для этого используются различные варианты метода load.
        // Чтобы загрузить файл из raw, необходимо указать Context, ID raw-файла и приоритет.
        // Приоритет пока что также игнорируется системой, рекомендуется передавать туда 1.
        //Метод load возвращает нам ID загруженного файла. Используя этот ID, мы будем проигрывать файл.
        soundIdShot = sp.load(this, R.raw.shot, 1);
    }

    // нахождение views и все такое
    private void turnOnViewElements() {
        startGame = findViewById(R.id.startGame);
        startGame.setOnClickListener(this);

        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);

        quitGame = findViewById(R.id.quitGame);
        quitGame.setOnClickListener(this);

        aboutUs = findViewById(R.id.aboutUs);
        aboutUs.setOnClickListener(this);
    }

    //обработчики нажатий на кнопки в главном меню
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //начать игру
            case R.id.startGame:
                sp.play(soundIdShot, 1, 1, 0, 0, 1);
                intent = new Intent(this, Levels.class);
                startActivity(intent);
                break;
            //настройки
            case R.id.settings:
                sp.play(soundIdShot, 1, 1, 0, 0, 1);
                intent = new Intent(this, SettingsMenu.class);
                startActivity(intent);
                break;
            //выйти из игры
            case R.id.quitGame:
                sp.play(soundIdShot, 1, 1, 0, 0, 1);
                finishAffinity();
                break;
            //про нас
            case R.id.aboutUs:
                sp.play(soundIdShot, 1, 1, 0, 0, 1);
                intent = new Intent(this, AboutUs.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}