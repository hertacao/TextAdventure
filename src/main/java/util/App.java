package util;

import objects.Scene;
import process.*;

/**
 * Created by Herta on 19.01.2018.
 */
public class App {
    public static void main(String[] args) {
        // builds Objects
        Scene s1 = new Scene("living room");
        Scene s2 = new Scene("kitchen");
        Scene s3 = new Scene("bedroom");

        Game game = new Game(s1);
        Searcher searcher = new Searcher();
        Handler handler = new Handler();
        Executer executer = new Executer(searcher);

        while(true) {
            searcher.search();
            handler.handle();

        }
    }
}
