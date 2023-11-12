package hbv501g.Classes;

import java.util.HashMap;

public class Stats {
    private int holesInOne;
    private int numberOfGamesPlayed;
    private HashMap<Long, Double> averageScorePerField;
    private double averageScorePerHole;

    public Stats() {}

    public Stats(int holesInOne, int numberOfGamesPlayed, HashMap<Long, Double> averageScorePerField, double averageScorePerHole) {
        this.holesInOne = holesInOne;
        this.numberOfGamesPlayed = numberOfGamesPlayed;
        this.averageScorePerField = averageScorePerField;
        this.averageScorePerHole = averageScorePerHole;
    }

    public int getHolesInOne() {
        return holesInOne;
    }

    public void setHolesInOne(int holesInOne) {
        this.holesInOne = holesInOne;
    }

    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }

    public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
        this.numberOfGamesPlayed = numberOfGamesPlayed;
    }

    public HashMap<Long, Double> getAverageScorePerField() {
        return averageScorePerField;
    }

    public void setAverageScorePerField(HashMap<Long, Double> averageScorePerField) {
        this.averageScorePerField = averageScorePerField;
    }

    public double getAverageScorePerHole() {
        return averageScorePerHole;
    }

    public void setAverageScorePerHole(double averageScorePerHole) {
        this.averageScorePerHole = averageScorePerHole;
    }
}
