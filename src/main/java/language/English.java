package language;

import command.*;
import util.*;

import java.util.*;

public class English extends Language{

    @Override
    public Map<Token, List<String>> createToken() {
        this.tokenMap = new HashMap<>();
        this.tokenMap.put(RelativeDirection.RIGHT, Arrays.asList("right"));
        this.tokenMap.put(RelativeDirection.LEFT, Arrays.asList("left"));
        this.tokenMap.put(RelativeDirection.FRONT, Arrays.asList("front"));
        this.tokenMap.put(RelativeDirection.BACK, Arrays.asList("back"));

        this.tokenMap.put(CardinalDirection.NORTH, Arrays.asList("north"));
        this.tokenMap.put(CardinalDirection.NORTHEAST, Arrays.asList("northeast", "north east"));
        this.tokenMap.put(CardinalDirection.EAST, Arrays.asList("east"));
        this.tokenMap.put(CardinalDirection.SOUTHEAST, Arrays.asList("southeast", "south east"));
        this.tokenMap.put(CardinalDirection.SOUTH, Arrays.asList("south"));
        this.tokenMap.put(CardinalDirection.SOUTHWEST, Arrays.asList("southwest", "south west"));
        this.tokenMap.put(CardinalDirection.WEST, Arrays.asList("west"));
        this.tokenMap.put(CardinalDirection.NORTHWEST, Arrays.asList("northwest", "north west"));

        this.tokenMap.put(BaseAction.LOOK, Arrays.asList("look at", "look"));
        this.tokenMap.put(BaseAction.EXAMINE, Arrays.asList("examine"));
        this.tokenMap.put(BaseAction.PICK_UP, Arrays.asList("pick up", "take"));
        this.tokenMap.put(BaseAction.OPEN, Arrays.asList("open"));
        this.tokenMap.put(BaseAction.CLOSE, Arrays.asList("close"));
        this.tokenMap.put(BaseAction.PUSH, Arrays.asList("push"));
        this.tokenMap.put(BaseAction.PULL, Arrays.asList("pull"));
        this.tokenMap.put(BaseAction.GO, Arrays.asList("go to", "go", "walk")); //add go
        this.tokenMap.put(BaseAction.TALK, Arrays.asList("talk to", "talk"));

        this.tokenMap.put(Action2P.USE, Arrays.asList("use", "turn"));
        this.tokenMap.put(Action2P.GIVE, Arrays.asList("give to", "give", "hand"));
        this.tokenMap.put(Action2P.COMBINE, Arrays.asList("combine"));

        this.tokenMap.put(Action2PString.ENTER, Arrays.asList("enter", "type"));

        this.tokenMap.put(Routine.ENTER, Arrays.asList("enter", "go through"));
        this.tokenMap.put(Routine.MOVE, Arrays.asList("move to", "move"));
        this.tokenMap.put(Routine.LOOK_AROUND, Arrays.asList("look around"));

        this.tokenMap.put(Status.INVENTORY, Arrays.asList("inventory", "bag", "my things", "what carry"));
        this.tokenMap.put(Status.LOCATION, Arrays.asList("location", "where i"));

        this.tokenMap.put(BaseControl.HELP, Arrays.asList("help"));
        this.tokenMap.put(BaseControl.EXIT, Arrays.asList("exit"));
        this.tokenMap.put(BaseControl.HINT, Arrays.asList("hint"));

        this.tokenMap.put(Logic.YES, Arrays.asList("yes"));
        this.tokenMap.put(Logic.NO, Arrays.asList("no"));

        return this.tokenMap;
    }

    @Override
    protected Map<Word, String> createWords() {
        this.wordMap = new HashMap<>();
        this.wordMap.put(Word.YOU, "you");
        this.wordMap.put(Word.CANNOT, "can't");
        this.wordMap.put(Word.CAN, "can");
        this.wordMap.put(Word.WITH, "with");
        return this.wordMap;
    }

    @Override
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
