package object.strategy.openclose;

import command.BaseAction;
import language.Article;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public class NoCloseStrategy implements CloseStrategy {

    private static final NoCloseStrategy instance = new NoCloseStrategy();

    private NoCloseStrategy() {
    }

    public static NoCloseStrategy getInstance() {
        return instance;
    }

    @Override
    public Response close(String label) {
        return new Response(false, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.CLOSE, Article.DEFINITE, label));
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
