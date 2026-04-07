import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author feng3302
 * @version 0.1.0
 * @Since 1/29/26
 **/
public class Jotto {
    private static final int WORD_SIZE = 5;
    private static final boolean DEBUG = true;

    private final ArrayList<String> playGuesses = new ArrayList<String>();
    private final ArrayList<String> playedWords = new ArrayList<String>();
    private final ArrayList<String> wordList;

    private String currentWord;
    private String fileName;

    private int score;

    public Jotto(String filename) {
        this.fileName = filename;
        this.wordList = readWords();
    }

    public boolean pickWord() {
        return false;
    }

    public String showWordList() {
        return "";
    }

    public ArrayList<String> showPlayerGuesses() {
        return new ArrayList<String>() {{
            add("Hello");
            add("World!");
        }};
    }

    void playerGuessScores(ArrayList<String> guesses) {

    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayList<String> readWords() {
        File f = new File(this.fileName);

        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                this.wordList.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception.");
        }

        return new ArrayList<String>(this.wordList);
    }

    public void play() {
        Scanner input = new Scanner(System.in);

        boolean gameRunning = true;

        while (gameRunning) {
            System.out.print("Menu\n");

            String choice = input.nextLine().toLowerCase();

            if (choice.equals("1") || choice.equals("one")) {
                if (pickWord()) {
                    guess();
                } else {
                    showPlayerGuesses();
                }
            } else if (choice.equals("2") || choice.equals("two")) {
                showWordList();
            } else if (choice.equals("3") || choice.equals("three")) {
                showPlayedWords();
            } else if (choice.equals("4") || choice.equals("four")) {
                showPlayerGuesses();
            } else if (choice.equals("zz")) {
                gameRunning = false;
            } else {
                System.out.println("Invalid choice. Try again\n");
            }
        }


    }

    int guess() {
        return 0;
    }

    public ArrayList<String> getPlayWords() {
        return new ArrayList<String>(this.playedWords);
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public int getLetterCount(String word) {
        return 0;
    }

    public String showPlayedWords() {
        return "PlayedWords";
    }

    public boolean addPlayerGuess(String playerGuess) {
        return false;
    }

    void updateWordList() {

    }


}
