package object.strategy.pushpull;

import command.BaseAction;
import language.Article;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public class NoPullStrategy implements PullStrategy{
    private static final NoPullStrategy instance = new NoPullStrategy();

    private NoPullStrategy() {
    }

    public static NoPullStrategy getInstance() {
        return instance;
    }

    @Override
    public Response pull(String label) {
        return new Response(false, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.PULL, Article.DEFINITE, label));
    }
}
