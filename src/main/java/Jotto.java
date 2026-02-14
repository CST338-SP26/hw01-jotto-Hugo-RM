import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author feng3302
 * @version 0.1.0
 * @Since 1/29/26
 **/
public class Jotto {
    private static final int WORD_SIZE = 5;
    private static final boolean DEBUG = true;

    private final ArrayList<String> playGuesses = new ArrayList<>();
    private final ArrayList<String> playedWords = new ArrayList<>();
    private final ArrayList<String> wordList = new ArrayList<>();

    private String currentWord;
    private String fileName;

    private int score;

    public Jotto(String filename) {
        this.fileName = filename;
        readWords();
    }

    public boolean pickWord() {
        Random rand = new Random();
        currentWord = wordList.get(rand.nextInt(wordList.size()));

        if (playedWords.contains(currentWord) && playedWords.size() == wordList.size()) {
            System.out.println("You've guessed them all!");
            return false;
        }

        if (playedWords.contains(currentWord) && playedWords.size() != wordList.size()) {
            return pickWord();
        }

        playedWords.add(currentWord);

        if (DEBUG) {
            System.out.println(currentWord);
        }

        return true;

    }

    public String showWordList() {
        StringBuilder response = new StringBuilder("Current word list:\n");

        for (String word : wordList) {
            response.append(word);
            response.append('\n');
        }

        return response.toString();
    }

    public ArrayList<String> showPlayerGuesses() {
        Scanner input = new Scanner(System.in);

        if (playGuesses.isEmpty()) {
            System.out.println("No words have been played.");

            return playGuesses;
        } else {
            System.out.println("Current player guesses:");
            for (String word : playGuesses) {
                System.out.println(word);
            }
        }

        System.out.println("Would you like to add the words to the word list? (y/n)\n");

        if (input.next().equalsIgnoreCase("y")) {
            updateWordList();
            showWordList();
        }

        return playGuesses;
    }

    void playerGuessScores(ArrayList<String> guesses) {
        for (String guess : guesses) {
            int score = getLetterCount(guess);
            System.out.printf("%s: %d%n", guess, score);
        }
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayList<String> readWords() {
        File f = new File(this.fileName);

        try (Scanner input = new Scanner(f)) {
            while (input.hasNextLine()) {
                this.wordList.add(input.nextLine());
            }
            return new ArrayList<>(this.wordList);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception.");
            return new ArrayList<>();
        }
    }

    public void play() {
        boolean gameRunning = true;

        Scanner input = new Scanner(System.in);

        while (gameRunning) {
            System.out.print("Welcome to the game.\n" +
                    "Current Score: " + this.score +
                    "\n=-=-=-=-=-=-=-=-=-=-=\n" +
                    "Choose one of the following:\n" +
                    "1:\t Start the game\n" +
                    "2:\t See the word list\n" +
                    "3:\t See the chosen words\n" +
                    "4:\t Show Player guesses\n" +
                    "zz to exit\n" +
                    "=-=-=-=-=-=-=-=-=-=-=\n" +
                    "What is your choice: "
            );

            String choice = input.nextLine().toLowerCase();

            switch (choice) {
                case "1", "one" -> {
                    if (pickWord()) {
                        this.score = guess();
                        System.out.println(this.score);
                    } else {
                        showPlayerGuesses();
                    }
                }
                case "2", "two" -> showWordList();
                case "3", "three" -> showPlayedWords();
                case "4", "four" -> showPlayerGuesses();
                case "zz" -> gameRunning = false;
                default -> {
                    System.out.printf("I don't know what %s is.%n", choice);
                    System.out.print("Press enter to continue");
                }
            }
        }
    }

    int guess() {
        ArrayList<String> currentGuesses = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int letterCount = 0;
        int score = WORD_SIZE + 1;
        String wordGuess;

        while (true) {
            System.out.printf("Current Score: %d%n", score);
            System.out.print("What is your guess (q to quit): ");

            wordGuess = input.nextLine().trim().toLowerCase();

            if (wordGuess.equals("q")) {
                score = Math.min(score, 0);
                break;
            }

            if (wordGuess.length() != WORD_SIZE) {
                System.out.printf("Word must be %d characters (%s is %d)%n", WORD_SIZE, wordGuess, wordGuess.length());
                continue;
            }

            if (playGuesses.contains(wordGuess)) {
                System.out.printf("%s has already been guessed, please try another word%n", wordGuess);
                continue;
            }

            if (addPlayerGuess(wordGuess)) {
                System.out.printf("DINGDINGDING!!! the word was %s%n", wordGuess);

                currentGuesses.add(wordGuess);
                playerGuessScores(currentGuesses);

                return score;
            }

            currentGuesses.add(wordGuess);
            letterCount = getLetterCount(wordGuess);

            if (letterCount != WORD_SIZE) {
                System.out.printf("%s has a Jotto score of %d%n", wordGuess, letterCount);
            } else {
                System.out.printf("CLOSE! %s is an anagram%n", wordGuess);
            }

            score--;
            playerGuessScores(currentGuesses);
        }

        return score;
    }

    public ArrayList<String> getPlayedWords() {
        return new ArrayList<>(this.playedWords);
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public int getLetterCount(String wordGuess) {
        String guess = wordGuess.toLowerCase();

        int count = 0;

        if (guess.equals(currentWord)) {
            return 5;
        }

        ArrayList<Character> uniqueChars = new ArrayList<>();
        for (Character c : currentWord.toCharArray()) {
            if (!uniqueChars.contains(c)) {
                uniqueChars.add(c);
            }
        }

        for (Character c : guess.toCharArray()) {
            if (uniqueChars.contains(c)) {
                uniqueChars.remove(c);
                count++;
            }
        }

        return count;
    }

    public String showPlayedWords() {
        StringBuilder response = new StringBuilder();

        if (this.playedWords.isEmpty()) {
            response.append("No words have been played.");
        } else {
            response.append("Current list of played words:\n");
            for (String word : playedWords) {
                response.append(word);
                response.append('\n');
            }
        }

        return response.toString();
    }

    public boolean addPlayerGuess(String playerGuess) {
        if (!playGuesses.contains(playerGuess)) {
            playGuesses.add(playerGuess);
            return true;
        }
        return false;
    }

    void updateWordList() {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String word : playGuesses) {
                if (!wordList.contains(word)) {
                    wordList.add(word);
                }
            }

            for (String word : wordList) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }


}
