package object.strategy.talk;

import command.BaseAction;
import language.Article;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public class NoTalkStrategy implements TalkStrategy{
    private static final NoTalkStrategy instance = new NoTalkStrategy();

    private NoTalkStrategy() {
    }

    public static NoTalkStrategy getInstance() {
        return instance;
    }
    @Override
    public Response talk(String label) {
        return new Response(false, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.TALK, Article.DEFINITE, label));
    }
}
