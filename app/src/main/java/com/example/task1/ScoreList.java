package com.example.task1;

import java.util.ArrayList;

public class ScoreList
{
    private String name = "";
    private String score = "";
    private ArrayList<Score> scoreList = new ArrayList<>();


    public ScoreList() {
    }

    public String getName() {
        return name;
    }

    public ScoreList setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<Score> getScore() {
        return scoreList;
    }

    public ScoreList setScore(String score) {
        this.score = score;
        return this;
    }


    public ScoreList setScores(ArrayList<Score> scores) {
        this.scoreList = scores;
        return this;
    }

    public void addScore(String newScore,double latitude, double longtitude)
    {
        Score score = new Score();
        score.setScore(newScore);
        score.setLongtitude(longtitude);
        score.setLatitude(latitude);
        scoreList.add(score);
    }

    @Override
    public String toString() {
        return "ScoreList{" +
                "name='" + name + '\'' +
                ", score =" + score +
                ", records =" + scoreList +
                '}';
    }



}
