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
        DEBUG,
        TEST
    }

    public static void main(String[] args) {
        Mode mode = Mode.DEBUG;
        Language language = new English();
        AdvStringBuilder.setLanguage(language);
        AdvObjectBuilder objectBuilder = new AdvObjectBuilder();
        objectBuilder.build();

        if(mode == Mode.DEBUG) {
            System.out.println(objectBuilder.toString());
            System.out.println("============================================");
        }

        Game game = new Game(objectBuilder.getScenes().getFirst());
        game.setRunning(true);
        Executer executer = new Executer();
        Parser parser = new Parser(language, executer, game);
        Builder builder = new Builder();

        System.out.println("Welcome to a new adventure!");

        while(game.isRunning()) {
            InternalMessage im = parser.parse();
            Request request = builder.buildRequest(im);
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
