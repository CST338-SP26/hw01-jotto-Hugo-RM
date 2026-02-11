

import java.util.ArrayList;

/**
 * Make a branch and start! :)
 */
public class Driver {
    public static void main(String[] args) {
        String filepath = "oiled.txt";
//        String filepath = "debug.txt";
//        String filepath = "wordList.txt";
        Jotto game = new Jotto(filepath);


        System.out.println(game.readWords());
//        System.out.println(game.readWords());
//        game.updateWordList();
    }
}
