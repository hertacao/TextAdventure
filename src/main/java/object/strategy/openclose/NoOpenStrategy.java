package object.strategy.openclose;

import command.BaseAction;
import language.Article;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public class NoOpenStrategy implements OpenStrategy {
    private static final NoOpenStrategy instance = new NoOpenStrategy();

    private NoOpenStrategy() {
    }

    public static NoOpenStrategy getInstance() {
        return instance;
    }

    @Override
    public Response open(String label) {
        return new Response(false, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.OPEN, Article.DEFINITE, label));
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
