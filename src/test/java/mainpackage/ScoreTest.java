package mainpackage;
import Management.Interface.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
public class ScoreTest {
    @BeforeEach
    public void resetScore(){
        Score.getInstance().resetInstance();
    }

    @TempDir
    Path tempDir;

    @Test
    public void testReadHighScoreFile() throws IOException {
        File tempFile = tempDir.resolve("HighScore.txt").toFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("25");
        writer.close();

        Score.getInstance().readHighScoreFile(tempDir.resolve("HighScore.txt").toString());

        assertEquals(25, Score.getInstance().getHighScore());
    }

    @Test
    public void testUpdateHighScoreFile() throws IOException {
        File tempFile = tempDir.resolve("HighScore.txt").toFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("25");
        writer.close();

        Score.getInstance().setHighScore(26);
        Score.getInstance().updateHighScoreFile(tempDir.resolve("HighScore.txt").toString());

        assertEquals(26, Score.getInstance().getHighScore());
    }

    @Test
    public void testSetScore(){
        final int currentScore = Score.getInstance().getScore();
        Score.getInstance().setScore(currentScore+1);
        assertEquals(currentScore+1,Score.getInstance().getScore());
    }
}
