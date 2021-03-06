package com.example.mybookgame.mathgame.database_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybookgame.R;
import com.example.mybookgame.mathgame.questions_and_logic.MathGame;
import com.example.mybookgame.mathgame.results.Results_activity;

public class SaveResults extends AppCompatActivity {
    EditText et_PersonName;
    Button btn_save_ur_results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_results);

        pickViews();

        btn_save_ur_results.setOnClickListener(btn_save_ur_results_clicked); //обработчик
    }

    View.OnClickListener btn_save_ur_results_clicked = new View.OnClickListener() {
        DB_Template template;
        DBHelper dbHelper;
        Intent intent;

        @Override
        public void onClick(View v) {
            dbHelper = new DBHelper(SaveResults.this);

            int result = MathGame.result;//значение очков из игры сделали статической переменной и обращаемся к ней в MathGame
            String name = et_PersonName.getText().toString();

            if (!name.isEmpty()) {
                template = new DB_Template(-1, et_PersonName.getText().toString(), result);
                boolean add = dbHelper.add(template);// добавляем результат
                Toast.makeText(SaveResults.this, "Результаты сохранены : \n" + template.toString() + "\nИзменения занесены в таблицу результатов", Toast.LENGTH_LONG).show();

                intent = new Intent(SaveResults.this, Results_activity.class);
                finish();
                startActivity(intent);

                btn_save_ur_results.setEnabled(false);
                btn_save_ur_results.setVisibility(View.INVISIBLE);
            } else {
                Toast.makeText(SaveResults.this, "Имя не может быть пустым!", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private void pickViews() {
        et_PersonName = findViewById(R.id.et_PersonName);
        btn_save_ur_results = findViewById(R.id.btn_save_ur_results);
    }
}