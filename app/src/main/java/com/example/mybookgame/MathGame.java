package com.example.mybookgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MathGame extends AppCompatActivity implements View.OnClickListener{

    Button startMathGame, btn_answer1, btn_answer2, btn_answer3, btn_answer4;
    TextView tv_timer, tv_score, tv_questions, tv_bottomScoreResult;
    ProgressBar pb_timer;

    LogicOfTheGameMath game = new LogicOfTheGameMath();

    int seconds = 15; // переменная для удобного отображения времени на tv_timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_game);

        startMathGame = findViewById(R.id.startMathGame);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);
        btn_answer4 = findViewById(R.id.btn_answer4);

        pb_timer = findViewById(R.id.pb_timer);

        tv_timer = findViewById(R.id.tv_timer);
        tv_score = findViewById(R.id.tv_score);
        tv_questions = findViewById(R.id.tv_questions);
        tv_bottomScoreResult = findViewById(R.id.tv_bottomScoreRezult);

        tv_timer.setText("Время");
        tv_questions.setText("");
        tv_bottomScoreResult.setText("Начинай уже!");
        tv_score.setText("Результат");

        startMathGame.setOnClickListener(clickStartGameButton);
        btn_answer1.setOnClickListener(this);
        btn_answer2.setOnClickListener(this);
        btn_answer3.setOnClickListener(this);
        btn_answer4.setOnClickListener(this);

//        btn_answer1.setOnTouchListener(this);
//        btn_answer2.setOnTouchListener(this);
//        btn_answer3.setOnTouchListener(this);
//        btn_answer4.setOnTouchListener(this);

        btn_answer1.setEnabled(false);
        btn_answer2.setEnabled(false);
        btn_answer3.setEnabled(false);
        btn_answer4.setEnabled(false);

        btn_answer1.setText("");
        btn_answer2.setText("");
        btn_answer3.setText("");
        btn_answer4.setText("");
    }

    @Override
    public void onClick(View v) {
        Button buttonCLicked = (Button) v;
        int answerSelected = Integer.parseInt(buttonCLicked.getText().toString());
        game.checkAnswer(answerSelected);
        tv_score.setText(Integer.toString(game.getScore()));
        startGame();
    }

    View.OnClickListener clickStartGameButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button startButton = (Button) v;
            startButton.setVisibility(View.INVISIBLE);
            seconds = 15;
            game = new LogicOfTheGameMath();
            startGame();


            //запустим обратный отчет для ползунка
            countDownTimer.start();
        }
    };

    private void startGame() {
        game.makeNewQuestion();
        ArrayList<Integer> answers = game.getCurrentQuestion().getAnswerList();

        btn_answer1.setText(Integer.toString(answers.get(0)));
        btn_answer2.setText(Integer.toString(answers.get(1)));
        btn_answer3.setText(Integer.toString(answers.get(2)));
        btn_answer4.setText(Integer.toString(answers.get(3)));

        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);
        btn_answer4.setEnabled(true);

        tv_score.setText("0");
        tv_questions.setText(game.getCurrentQuestion().getQuestionPhrase());
        tv_bottomScoreResult.setText(game.getNumberCorrect() + "/" + (game.getTotalQuestions() - 1));
    }

    CountDownTimer countDownTimer = new CountDownTimer(16000, 1000) {
        // 15000 - общее количетсов миллисекунд для ползунка,
        // 1000 - миллисекунд для одного скачка ползунка вперед
        @Override
        public void onTick(long millisUntilFinished) {
            seconds--;
            tv_timer.setText(Integer.toString(seconds + 1) + " сек");
            pb_timer.setProgress(15 - seconds);
        }

        @Override
        public void onFinish() {
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            btn_answer4.setEnabled(false);
            tv_bottomScoreResult.setWidth(1080);
            tv_bottomScoreResult.setBackgroundResource(R.color.myGreen);
            tv_bottomScoreResult.setText("\t\t\t\t\t\t\t\tВремя вышло! Результат: \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                    game.getNumberCorrect() + "/" + (game.getTotalQuestions() - 1));

            tv_timer.setText(Integer.toString(0) + " сек");
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