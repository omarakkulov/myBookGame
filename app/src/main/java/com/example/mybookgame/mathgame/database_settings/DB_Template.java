package com.example.mybookgame.mathgame.database_settings;

public class DB_Template {
    private int id;
    private String name;
    private int result;

    public DB_Template(int id, String name, int result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Имя ='" + name + '\'' +
                ", Результат = " + result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
