package com.example.mybookgame.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybookgame.R;

// реализуем интерфейсы для обработки нажатия клавиш в окне настроек, интерфейс для обработки нажатий на ползунок, интерфейс для popupMenu
public class SettingsMenu extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, PopupMenu.OnMenuItemClickListener {
    //кнопки на экране
    private Button font_size, back;
    //    private CheckBox volume_on_off;
    private Context context;
    private SeekBar seekBar;
    private TextView brightness_textView;
    private int brightness_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        brightness_textView = findViewById(R.id.brightnessLevel);

        seekBar = findViewById(R.id.seekBarForBrightness);

        //какой-то код со stackoverflow для определения яркости игры
        context = getApplicationContext();
        brightness_int = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 0);

        // обработка ползунка
        seekBar.setProgress(brightness_int);
        seekBar.setOnSeekBarChangeListener(this);

        font_size = findViewById(R.id.font_size);
        font_size.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.font_size:
                PopupMenu popupMenu = new PopupMenu(this, v);
                popupMenu.setOnMenuItemClickListener(SettingsMenu.this);
                popupMenu.inflate(R.menu.popupmenu);
                popupMenu.show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Выбран мелкий шрифт", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Выбран средний шрифт", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Выбран большой шрифт", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }

    //здесь метод onProgressChanged будет устанавливать якрость в зависимости от положения ползунка
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 0);
        brightness_textView.setText(String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

}
