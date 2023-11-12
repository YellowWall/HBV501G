package hbv501g.Classes;

public class Stats {
    private int holesInOne;
    private int numberOfGamesPlayed;
    private double averageScorePerGame;
    private double averageScorePerHole;

    public Stats() {}

    public Stats(int holesInOne, int numberOfGamesPlayed, double averageScorePerGame, double averageScorePerHole) {
        this.holesInOne = holesInOne;
        this.numberOfGamesPlayed = numberOfGamesPlayed;
        this.averageScorePerGame = averageScorePerGame;
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

    public double getAverageScorePerGame() {
        return averageScorePerGame;
    }

    public void setAverageScorePerGame(double averageScorePerGame) {
        this.averageScorePerGame = averageScorePerGame;
    }

    public double getAverageScorePerHole() {
        return averageScorePerHole;
    }

    public void setAverageScorePerHole(double averageScorePerHole) {
        this.averageScorePerHole = averageScorePerHole;
    }
}
