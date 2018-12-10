package object.strategy.pushpull;

import command.BaseAction;
import language.Article;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public class NoPushStrategy implements PushStrategy{
    private static final NoPushStrategy instance = new NoPushStrategy();

    public static NoPushStrategy getInstance() {
        return instance;
    }

    @Override
    public Response push(String label) {
        return new Response(false, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.PUSH, Article.DEFINITE, label));
    }
}
