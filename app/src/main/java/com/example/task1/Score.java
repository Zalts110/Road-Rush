package com.example.task1;

public class Score implements Comparable<Score>
{
    private String score = "";

    public Score() {}


    public Score setScore(String score)
    {
        this.score = score;
        return this;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Score" +
                ", score = " + score
              ;
    }

    @Override
    public int compareTo(Score o) {
        int thisScore = Integer.parseInt(o.getScore());
        int arrayScore = Integer.parseInt(this.getScore());
        return thisScore - arrayScore;
    }
}




