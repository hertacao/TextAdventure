package language;

import command.*;
import util.CardinalDirection;
import util.RelativeDirection;
import util.Token;
import util.Word;

import java.util.*;

public class English extends Language{

    @Override
    public Map<Token, List<String>> createToken() {
        Map<Token, List<String>> token = new HashMap<>();
        token.put(RelativeDirection.RIGHT, Arrays.asList("right"));
        token.put(RelativeDirection.LEFT, Arrays.asList("left"));
        token.put(RelativeDirection.FRONT, Arrays.asList("front"));
        token.put(RelativeDirection.BACK, Arrays.asList("back"));

        token.put(CardinalDirection.NORTH, Arrays.asList("north"));
        token.put(CardinalDirection.NORTHEAST, Arrays.asList("northeast", "north east"));
        token.put(CardinalDirection.EAST, Arrays.asList("east"));
        token.put(CardinalDirection.SOUTHEAST, Arrays.asList("southeast", "south east"));
        token.put(CardinalDirection.SOUTH, Arrays.asList("south"));
        token.put(CardinalDirection.SOUTHWEST, Arrays.asList("southwest", "south west"));
        token.put(CardinalDirection.WEST, Arrays.asList("west"));
        token.put(CardinalDirection.NORTHWEST, Arrays.asList("northwest", "north west"));

        token.put(BaseAction.LOOK, Arrays.asList("look at", "look"));
        token.put(BaseAction.EXAMINE, Arrays.asList("examine"));
        token.put(BaseAction.PICK_UP, Arrays.asList("pick up", "take"));
        token.put(BaseAction.OPEN, Arrays.asList("open"));
        token.put(BaseAction.CLOSE, Arrays.asList("close"));
        token.put(BaseAction.PUSH, Arrays.asList("push"));
        token.put(BaseAction.PULL, Arrays.asList("pull"));
        token.put(BaseAction.GO, Arrays.asList("go to", "go", "walk")); //add go
        token.put(BaseAction.TALK, Arrays.asList("talk"));

        token.put(Action2P.USE, Arrays.asList("use", "turn"));
        token.put(Action2P.GIVE, Arrays.asList("give", "hand"));
        token.put(Action2P.COMBINE, Arrays.asList("combine"));

        token.put(Routine.ENTER, Arrays.asList("enter", "go through"));
        token.put(Routine.MOVE, Arrays.asList("move"));
        token.put(Routine.LOOK_AROUND, Arrays.asList("look around"));

        token.put(Status.INVENTORY, Arrays.asList("inventory", "bag", "my things", "what carry"));
        token.put(Status.LOCATION, Arrays.asList("location", "where i"));

        token.put(BaseControl.HELP, Arrays.asList("help"));
        token.put(BaseControl.EXIT, Arrays.asList("exit"));
        token.put(BaseControl.HINT, Arrays.asList("hint"));

        return token;
    }

    @Override
    public Map<Word, String> createStrings() {
        Map<Word, String> strings = new HashMap<>();
        strings.put(Word.YOU, "you");
        strings.put(Word.CANNOT, "can't");
        return strings;
    }

    public String getArticle(String label, Article article_type) {
        switch(article_type) {
            case DEFINITE: return "the";
            case INDEFINITE: return "a";
            case RELATIVE_PRONOUN: return "which";
            case NONE: return "";
        }
        return null;
    }
}
