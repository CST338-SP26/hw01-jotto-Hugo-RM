import java.utils.ArrayList
/**
 * @author feng3302
 * @version 0.1.0
 * @Since 1/29/26
 **/
public class Jotto {
    public Jotto(String filename) {
        this.DEBUG = true;
        this.WORD_SIZE = 5;
    }

    public boolean pickWord() {

    }

    public String showWordList() {

    }

    public ArrayList<String> showPlayerGuesses() {

    }

    void playerGuessScores(ArrayList<String>) {

    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayList<String> readWords(ArrayList<String> words) {

    }

    public void play() {

    }

    int guess() {

    }

    public ArrayList<String> getPlayWords() {

    }

    public String getCurrentWord() {

    }

    public int getLetterCount(String) {

    }

    public String showPlayedWords() {

    }

    public boolean addPlayerGuess(String playerGuess) {

    }

    void updateWordList() {

    }

    private static final int WORD_SIZE;
    private String currentWord;
    private int score;
    private final ArrayList<String> playGuesses;
    private final ArrayList<String> playedWords;
    private String fileName;
    private final ArrayList<String> wordList;
    private static final boolean DEBUG;
}
