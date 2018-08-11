package build;

import command.*;
import process.*;
import process.request.Request;
import util.AdvStringBuilder;
import language.English;
import language.Language;

/**
 * Created by Herta on 19.01.2018.
 */
public class App {

    public enum Mode {
        NORMAL,
        DEBUG
    }

    public static void main(String[] args) {
        Mode mode = Mode.NORMAL;
        Language language = new English();
        AdvStringBuilder.setLanguage(language);
        AdvObjectBuilder builder = new AdvObjectBuilder();
        builder.build();

        if(mode == Mode.DEBUG) {
            System.out.println(builder.toString());
            System.out.println("============================================");
        }

        Game game = new Game(builder.getScenes().getFirst());
        game.setRunning(true);
        Executer executer = new Executer();
        Parser parser = new Parser(language, executer, game);

        System.out.println("Welcome to a new adventure!");

        while(game.isRunning()) {
            Request request = parser.buildRequest();
            Response response = executer.invokeRequest(request, game);
            System.out.println(response.toString());

            if(mode == Mode.DEBUG) {
                System.out.println();
                System.out.println(parser.toString());
                System.out.println("----------------------------------------");
                System.out.println(request.toString());
                System.out.println("----------------------------------------");
                System.out.println(game.toString());
            }
        }
    }
}
