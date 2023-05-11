package com.example.task1;

public class Score implements Comparable<Score>
{
    private String score = "";
    private double latitude = 0;
    private double longtitude = 0;

    public Score() {
    }


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
                ", score = " + score  + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longtitude + '\''
              ;
    }

    @Override
    public int compareTo(Score o) {
        int thisScore = Integer.parseInt(o.getScore());
        int arrayScore = Integer.parseInt(this.getScore());
        return thisScore - arrayScore;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude){
        this.longtitude = longtitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongtitude(){
        return this.longtitude;
    }

}




