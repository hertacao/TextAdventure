package process;

import build.Game;
import command.*;
import lombok.Getter;
import lombok.Setter;
import object.interfaces.AdvObject;
import object.interfaces.Connector;
import object.Container;
import util.*;
import language.Language;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Searches the input String for Commands
 */
@Getter
@Setter
public class Parser {
    private Language language;
    private Scanner sc;
    private Game game;
    private Executer executer;
    private InternalMessage im;

    public Parser(Language language, Executer executer, Game game) {
        this.language = language;
        this.executer = executer;
        this.game = game;
        this.sc = new Scanner(System.in);
        this.im = new InternalMessage();
        this.im.setLast_obj(game.getLocation());
    }

    public InternalMessage parse() {
        this.im.clear();
        this.tokenize();
        this.classify(this.im.getInput());

        return this.im;
    }

    private void tokenize() {
        String line = sc.nextLine().toLowerCase();
        this.im.setInput(Arrays.asList(line.split(" ")));
    }
    
    private void classify(List<String> input) {
        // find tokens in input
        List<Token> tokenList = this.language.getKeywordTree().checkForMatch(input);
        // classifies in action, control and other
        for(Token token : tokenList) {
            if (token instanceof Action) {
                this.im.getAction().add((Action) token);
            } else if (token instanceof Control) {
                this.im.getControl().add((Control) token);
            } else {
                this.im.getOther().add(token);
            }
        }

        // find container objects in input
        for(Container container : game.getReachableContainer()) {
            // find if tokens are in input

            List<Token> itemList = container.getContentTree().checkForMatch(input);
            itemList.stream()
                    .map(token -> (AdvObject) token)
                    .forEach(this.im.getObject()::add);
        }

        // find scenes in input, homonym handling is not required
        game.getLocation().getReachableScene().stream()
                .filter(scene ->
                        scene.getReference().stream()
                                .anyMatch(input::contains))
                .forEach(this.im.getObject()::add);

        // find connectors in input
        Map<Connector, Direction> directionMap = game.getLocation().getDirectionMap().entrySet().stream()
                .filter(directionEntry ->
                        directionEntry.getKey().getReference().stream()
                                .anyMatch(input::contains))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // handles homonym in connectors
        Set<Connector> connector;
        if(directionMap.size() > 1) {
            connector = directionMap.entrySet().stream()
                    .filter(directionEntry -> this.getIm().getOther().contains(directionEntry.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());
        } else {
            connector = directionMap.keySet();
        }
        this.getIm().getObject().addAll(connector);
    }

}
