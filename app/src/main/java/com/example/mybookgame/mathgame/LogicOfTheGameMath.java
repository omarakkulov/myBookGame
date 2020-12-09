package com.example.mybookgame;

public class LogicOfTheGameMath {

//    private List<Question_MG> questions;
    private int numberCorrect, numberIncorrect, totalQuestions, score;
    private Question_MG currentQuestion;

    //обозначим начальные позиции
    public LogicOfTheGameMath() {
//        questions = new ArrayList<>();
        numberCorrect = 0;
        numberIncorrect = 0;
        score = 0;
        currentQuestion = new Question_MG(10);
    }

    public void makeNewQuestion() {
        currentQuestion = new Question_MG(totalQuestions * 2 + 5);
        totalQuestions++;
//        questions.add(currentQuestion);
    }

    //сравнение на правильность клика кнопки с определенным значением
    // если выбрали правильный ответ, то +10, иначе - 30
    public boolean checkAnswer(int submittedAnswer) {
        boolean isCorrect;
        if (currentQuestion.getAnswer() == submittedAnswer) {
            numberCorrect++;
            isCorrect = true;
        } else {
            numberIncorrect++;
            isCorrect = false;
        }
        score = numberCorrect * 10 - numberIncorrect * 30;

        return isCorrect;
    }


    //геттеры и сеттеры
//    public List<Question_MG> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(List<Question_MG> questions) {
//        this.questions = questions;
//    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question_MG getCurrentQuestion() {
        return currentQuestion;
    }

}
