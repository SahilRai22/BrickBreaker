package ac.uk.nottingham.comp2013_cw.game;


import javax.swing.*;
import java.io.*;

/**
 * @author Sahil Rai
 * This class uses score counting from method in wall class and then checks
 * for highscore.
 * */
public class Score {
    /**format for string to be outputted*/
    public static String highScore = "Null:0";
    /**counter for count*/
    public static int scoreCount;
    /**
     * @return gets back score count
     * */
    public int getScoreCount() { return scoreCount; }

    /**
     * This method is called when game is over to reset score
     * */
    public static void getScoreReset() {
        scoreCount = 0;
    }

    /**
     * This method checks to see if the score is greater than highscore
     * if it is it will write String into highscore.dat file which can't be edited
     * */
    public void scoreChecker() {
        if (highScore.equals(""))
            return;

        highScore = getHighScoreFile();
        if (scoreCount > Integer.parseInt(highScore.split(":")[1])) {
            String name = JOptionPane.showInputDialog("You set a new highscore!! Enter name:");
            highScore = name + ":" + scoreCount + "\n";

            File scoreFile = new File("src/highscore.txt");
            if (!scoreFile.exists()) {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            FileWriter fWriter;
            BufferedWriter bWriter = null;
            try {
                fWriter = new FileWriter(scoreFile, true);
                bWriter = new BufferedWriter(fWriter);
                bWriter.write(this.highScore);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bWriter!= null)
                        bWriter.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method reads highscore file so new score can be checked against old highscore
     * @return reads line
     *
     * */
    public String getHighScoreFile(){
        FileReader fReader;
        BufferedReader bReader = null;

        try{
            fReader = new FileReader("src/highscore.txt");
            bReader = new BufferedReader(fReader);

            String newHighScore = "";
            while ((highScore= bReader.readLine()) != null)
            {
                newHighScore = highScore;
            }
            return newHighScore;
        }
        catch(Exception e){
            return "Null:0"; // returns null and score 0
        }
        finally {
            try {
                if (bReader != null)
                    bReader.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}
