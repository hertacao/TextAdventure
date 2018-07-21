package util.languages;

import commands.*;
import lombok.Getter;
import util.Token;

import java.lang.reflect.Array;
import java.util.*;

public class English extends Language{

    @Override
    protected Map<Token, List<String>> createMap() {
        Map<Token, List<String>> token = new HashMap<>();
        token.put(BaseAction.LOOK, Arrays.asList("look"));
        token.put(BaseAction.EXAMINE, Arrays.asList("examine"));
        token.put(BaseAction.PICK_UP, Arrays.asList("pick up", "take"));
        token.put(BaseAction.OPEN, Arrays.asList("open"));
        token.put(BaseAction.CLOSE, Arrays.asList("close"));
        token.put(BaseAction.PUSH, Arrays.asList("push"));
        token.put(BaseAction.PULL, Arrays.asList("pull"));
        token.put(BaseAction.GO, Arrays.asList("go", "walk"));
        token.put(BaseAction.TALK, Arrays.asList("talk"));

        token.put(Action2P.USE, Arrays.asList("use", "turn"));
        token.put(Action2P.GIVE, Arrays.asList("give", "hand"));
        token.put(Action2P.COMBINE, Arrays.asList("combine"));

        token.put(Routine.ENTER, Arrays.asList("enter", "go through"));
        token.put(Routine.MOVE, Arrays.asList("move"));

        token.put(Status.INVENTORY, Arrays.asList("inventory", "bag", "my things", "what carry"));
        token.put(Status.LOCATION, Arrays.asList("location", "where I"));

        token.put(BaseControl.HELP, Arrays.asList("help"));
        token.put(BaseControl.EXIT, Arrays.asList("exit"));
        token.put(BaseControl.HINT, Arrays.asList("hint"));

        return token;
    }

    public boolean addToken (Token t, String... strings) {
        if (token.containsKey(t)) {
            token.get(t).addAll(Arrays.asList(strings));
            return true;
        } else {
            return false;
        }
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
