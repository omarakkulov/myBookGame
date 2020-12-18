package com.example.mybookgame.mathgame;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mybookgame.R;

import java.util.ArrayList;

public class MathGame extends AppCompatActivity implements View.OnClickListener {
    // 4 кнопки игры и кнопка начала игры
    Button startMathGame, btn_answer1, btn_answer2, btn_answer3, btn_answer4;

    // таблица результатов, кнопка ОК чтобы ввести имя в бд, само имя
    Button btn_results, btn_OK;
    // textView для пояснения введения имени в editText
    TextView tv_textPersonName;
    EditText editTextTextPersonName;// введите имя


    // textViews таймер, результаты выбора справа сверху, textView самих вопросов, textView результатов после игры
    TextView tv_timer, tv_score, tv_questions, tv_bottomScoreResult;

    //ползунок для времени
    ProgressBar pb_timer;

    // вложенный класс для логики самой игры
    LogicOfTheGameMath game = new LogicOfTheGameMath();

    int seconds = 15; // переменная для удобного отображения времени на tv_timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_game);

        // найдем наши элементы по id
        startMathGame = findViewById(R.id.startMathGame);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);
        btn_answer4 = findViewById(R.id.btn_answer4);

        pb_timer = findViewById(R.id.pb_timer);

        btn_OK = findViewById(R.id.btn_OK);
        btn_OK.setVisibility(View.INVISIBLE);
        btn_OK.setEnabled(false);
        tv_textPersonName = findViewById(R.id.tv_textPersonName);
        tv_textPersonName.setVisibility(View.INVISIBLE);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName.setEnabled(false);
        editTextTextPersonName.setVisibility(View.INVISIBLE);

        tv_timer = findViewById(R.id.tv_timer);
        tv_score = findViewById(R.id.tv_score);
        tv_questions = findViewById(R.id.tv_questions);
        tv_bottomScoreResult = findViewById(R.id.tv_bottomScoreResult);
        btn_results = findViewById(R.id.btn_results);

        tv_timer.setText("Время");
        tv_questions.setText("");
        tv_bottomScoreResult.setText("Начинай уже!");
        tv_score.setText("Баллы");

        //присвоим обработчики каждой кнопке
        startMathGame.setOnClickListener(clickStartGameButton);
        btn_answer1.setOnClickListener(this);
        btn_answer2.setOnClickListener(this);
        btn_answer3.setOnClickListener(this);
        btn_answer4.setOnClickListener(this);

        btn_results.setOnClickListener(clickResults);

        //здесь я чуть позже изменю кое что, пока не работает
//        btn_answer1.setOnTouchListener(this);
//        btn_answer2.setOnTouchListener(this);
//        btn_answer3.setOnTouchListener(this);
//        btn_answer4.setOnTouchListener(this);

        // в начале игры обозначим 4 кнопки как неработающие, иначе вылетает ошибка если кликаем
        btn_answer1.setEnabled(false);
        btn_answer2.setEnabled(false);
        btn_answer3.setEnabled(false);
        btn_answer4.setEnabled(false);

        btn_answer1.setText("");
        btn_answer2.setText("");
        btn_answer3.setText("");
        btn_answer4.setText("");
    }

    //так как мы реализуем интерфейс OnclickListener в нашем классе MathGame,
    // то сразу без создания лишних классов я реализовал в нем метод onClick для 4 кнопок с выбором ответа
    @Override
    public void onClick(View v) {
        Button buttonCLicked = (Button) v;
        int answerSelected = Integer.parseInt(buttonCLicked.getText().toString());
        game.checkAnswer(answerSelected);
        tv_score.setText(Integer.toString(game.getScore()));
        startGame();
    }

    // создаем обработчик для нажатия кнопки startGameButton для начала игры
    View.OnClickListener clickStartGameButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button startButton = (Button) v;

            startButton.setVisibility(View.INVISIBLE);
            btn_results.setVisibility(View.INVISIBLE);
            btn_results.setEnabled(false);

            btn_OK.setVisibility(View.INVISIBLE);
            btn_OK.setEnabled(false);

            seconds = 15;
            tv_score.setText("0");
            game = new LogicOfTheGameMath();
            startGame();

            //запустим обратный отчет для ползунка
            countDownTimer.start();

            editTextTextPersonName.setEnabled(false);
            editTextTextPersonName.setVisibility(View.INVISIBLE);
            tv_textPersonName.setVisibility(View.INVISIBLE);
        }
    };

    View.OnClickListener clickOKBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    // обработчки для результатов
    View.OnClickListener clickResults = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button results = (Button) v;
            Intent intent = new Intent(MathGame.this, Results_activity.class);
            startActivity(intent);
        }
    };

    // это практически главный метод нашей игры, создаем новый вопрос
    private void startGame() {
        game.makeNewQuestion();
        // массив с ответами на вычисления
        ArrayList<Integer> answers = game.getCurrentQuestion().getAnswerList();

        btn_answer1.setText(Integer.toString(answers.get(0)));
        btn_answer2.setText(Integer.toString(answers.get(1)));
        btn_answer3.setText(Integer.toString(answers.get(2)));
        btn_answer4.setText(Integer.toString(answers.get(3)));

        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);
        btn_answer4.setEnabled(true);

        tv_questions.setText(game.getCurrentQuestion().getQuestionPhrase());
        tv_bottomScoreResult.setText(game.getNumberCorrect() + "/" + (game.getTotalQuestions() - 1));
    }

    // создадим анонимный класс из абстрактного CountDown, в котором реализуем 2 метода
    CountDownTimer countDownTimer = new CountDownTimer(16000, 1000) {
        // 15000 - общее количетсов миллисекунд для ползунка,
        // 1000 - миллисекунд для одного скачка ползунка вперед
        @Override
        public void onTick(long millisUntilFinished) {
            seconds--;
            tv_timer.setText((seconds + 1) + " сек");
            pb_timer.setProgress(15 - seconds);
        }

        // этот метод будет выполняться после истечения времени игры
        @Override
        public void onFinish() {
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            btn_answer4.setEnabled(false);
            tv_bottomScoreResult.setWidth(1080);
            tv_bottomScoreResult.setBackgroundResource(R.color.myGreen);
            tv_bottomScoreResult.setText("Время вышло! Результат: " + game.getNumberCorrect() + "/" + (game.getTotalQuestions() - 1));

            tv_timer.setText((0) + " сек");
            pb_timer.setProgress(15);
            //как я понял, Handler это класс обработчик, который может совершить какое-то действие один раз после указанной задержки
            //задержкой тут является число 2000 в методе postDelayed()
            // то есть после того, как игра заканчивается, то через 2 секунды наша главная кнопка снова появится и мы можем начать играть заново
            // об этом методе хорошо расписано тут https://guides.codepath.com/android/Repeating-Periodic-Tasks
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startMathGame.setVisibility(View.VISIBLE);
                }
            }, 2000);

            editTextTextPersonName.setEnabled(true);
            editTextTextPersonName.setVisibility(View.VISIBLE);
            tv_textPersonName.setVisibility(View.VISIBLE);
            btn_OK.setVisibility(View.VISIBLE);
            btn_OK.setEnabled(true);
            btn_results.setVisibility(View.VISIBLE);
            btn_results.setEnabled(true);
        }
    };

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        Button button = (Button) v;
//        v.color
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                button.setBackgroundResource(R.color.RED);
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
////                button.setBackground(backGroundColor);
////                button.setBackgroundColor(Color.);
////                button.setBackgroundResource(R.color.myGreen);
//                break;
//        }
//        return false;
//    }
}