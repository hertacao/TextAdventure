package object.strategy.use;

import command.Action2P;
import language.Article;
import object.interfaces.AdvObject;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public class NoUseStrategy implements UseStrategy {
    private static final NoUseStrategy instance = new NoUseStrategy();

    private NoUseStrategy() {
    }

    public static NoUseStrategy getInstance() {
        return instance;
    }

    @Override
    public Response use(String label, AdvObject object) {
        return new Response(false, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, Action2P.USE, Article.DEFINITE, label, Word.WITH, object));
    }

    @Override
    public boolean isBlockingState() {
        return false;
    }

    @Override
    public String getState() {
        return null;
    }
}
