package build;

import process.*;
import process.request.Request;
import util.AdvStringBuilder;
import language.English;
import language.Language;

/**
 * Created by Herta on 19.01.2018.
 */
public class App {
    public static void main(String[] args) {
        Language language = new English();
        AdvStringBuilder.setLanguage(new English());
        AdvObjectBuilder builder = new AdvObjectBuilder();
        builder.build();

        Game game = new Game(builder.getScenes().getFirst());
        game.setRunning(true);
        Executer executer = new Executer();
        Parser parser = new Parser(language, executer, game);

        System.out.println("Welcome to a new adventure!");

        while(game.isRunning()) {
            Request request = parser.buildRequest();
            System.out.println(parser.toString());
            Response response = executer.invokeRequest(request, game);
            response.print();
            System.out.println(game.toString());
        }
    }
}
