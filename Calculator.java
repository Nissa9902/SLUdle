import java.util.*;

public class ScoreCalculator {
    private int currentStreak;
    private int maxStreak;
    private int totalGamesPlayed;
    private Map<Integer, Integer> guessCountFrequency;

    public ScoreCalculator() {
        currentStreak = 0;
        maxStreak = 0;
        totalGamesPlayed = 0;
        guessCountFrequency = new HashMap<>();
    }

    public void recordWin(int guessCount) {
        totalGamesPlayed++;
        guessCountFrequency.put(guessCount, guessCountFrequency.getOrDefault(guessCount, 0) + 1);

        // Update streak
        currentStreak++;
        maxStreak = Math.max(maxStreak, currentStreak);
    }

    public void recordLoss() {
        totalGamesPlayed++;
        currentStreak = 0;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public int getMaxStreak() {
        return maxStreak;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public Map<Integer, Integer> getGuessCountFrequency() {
        return guessCountFrequency;
    }
}