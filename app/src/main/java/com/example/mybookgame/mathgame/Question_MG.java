package com.example.mybookgame.mathgame;

import java.util.ArrayList;
import java.util.Collections;

public class Question_MG {
    // первое число из выражения, второе число из выражения и ответ
    private int firstNumb, secondNumb, answer;

    // лист ответов для 4-ех кнопок на экране, в котором один правильный
    private ArrayList<Integer> answerList;

    //позиция правильного ответа из листа answerList
    private int answerPos;

    //максимальное значение двух чисел из вопроса по типу "5 + 2" --> 5
    private int maxValue;

    //строковое представление вопроса "10 + 10 = "...
    private String questionPhrase;

    //в конструкторе будем создавать новый рандомный вопрос для вычисления двух чисел
    public Question_MG(int maxValue) {
        this.maxValue = maxValue;

//        int x = (int) (Math.random() * maxValue + 10);
        this.firstNumb = maxValue * 2 + (int) (Math.random() * maxValue);
        this.secondNumb = maxValue * 2 + (int) (Math.random() * maxValue);
//        this.secondNumb = (int) (Math.random() * maxValue);
        this.answer = this.firstNumb + this.secondNumb;

        this.questionPhrase = this.firstNumb + " + " + this.secondNumb + " = ";

        //позиция правильного ответа
        this.answerPos = (int) (Math.random() * 4);

        this.answerList = new ArrayList<>(4);

        // добавим в лист рандомные числа
        this.answerList.add(answer + 1);
        this.answerList.add(answer + 7);
        this.answerList.add(answer + 3);
        this.answerList.add(answer - 5);

        // перемешаем индексы чисел внутри листа
        Collections.shuffle(answerList);

        // теперь добавим в позицию answerPos число answer, то есть правильный ответ
        // понимаю, можно было бы воспользоваться обычным массивом, но я решил сделать через ArrayList
        answerList.add(answerPos, answer);
        answerList.remove(answerPos + 1);

        //this.answerArray = new int[]{0, 1, 2, 3};
        // this.answerArray[0] = answer + 1;
        // this.answerArray[1] = answer + 7;
        // this.answerArray[2] = answer + 3;
        // this.answerArray[3] = answer - 5;
        // answerArray[answerPos] = answer;
    }


    // геттеры и сеттеры
    public int getFirstNumb() {
        return firstNumb;
    }

    public void setFirstNumb(int firstNumb) {
        this.firstNumb = firstNumb;
    }

    public int getSecondNumb() {
        return secondNumb;
    }

    public void setSecondNumb(int secondNumb) {
        this.secondNumb = secondNumb;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public ArrayList<Integer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Integer> answerList) {
        this.answerList = answerList;
    }

    public int getAnswerPos() {
        return answerPos;
    }

    public void setAnswerPos(int answerPos) {
        this.answerPos = answerPos;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
}
