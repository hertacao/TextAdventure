package object.strategy.openclose;

import command.BaseAction;
import language.Article;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public enum OpenClosedState {
    CLOSED {
        @Override
        public Response open(BaseOpenCloseStrategy baseOpenCloseStrategy, String label) {
            baseOpenCloseStrategy.setOpenClosedState(OPEN);
            return new Response(true, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.CLOSE, Article.DEFINITE, label));
        }

        @Override
        public Response close(BaseOpenCloseStrategy baseOpenCloseStrategy, String label) {
            return new Response(false, "It's already closed.");
        }

    },
    OPEN {
        @Override
        public Response open(BaseOpenCloseStrategy baseOpenCloseStrategy, String label) {
            return new Response(false, "It's already open.");
        }

        @Override
        public Response close(BaseOpenCloseStrategy baseOpenCloseStrategy, String label) {
            baseOpenCloseStrategy.setOpenClosedState(CLOSED);
            return new Response(true, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.OPEN, Article.DEFINITE, label));
        }
    };

    public Response open(BaseOpenCloseStrategy baseOpenCloseStrategy, String label) {return null;}
    public Response close(BaseOpenCloseStrategy baseOpenCloseStrategy, String label) {return null;}
}
