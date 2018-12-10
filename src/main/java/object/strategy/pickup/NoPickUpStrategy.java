package object.strategy.pickup;

import command.BaseAction;
import language.Article;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public class NoPickUpStrategy implements PickUpStrategy {
    private static final NoPickUpStrategy instance = new NoPickUpStrategy();

    private NoPickUpStrategy() {
    }

    public static NoPickUpStrategy getInstance() {
        return instance;
    }

    @Override
    public Response pickUp(String label) {
        return new Response(false, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.PICK_UP, Article.DEFINITE, label));
    }
}
