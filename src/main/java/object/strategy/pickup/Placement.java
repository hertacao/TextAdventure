package object.strategy.pickup;

import command.BaseAction;
import language.Article;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public enum Placement {
    PICKED_UP {
        @Override
        public Response pickUp(BasePickUpStrategy basePickUpStrategy, String label) {
            return new Response(false, AdvStringBuilder.buildSentence(Word.YOU, "already", BaseAction.PICK_UP, "this"));
        }
    },
    AROUND {
        @Override
        public Response pickUp(BasePickUpStrategy basePickUpStrategy, String label) {
            basePickUpStrategy.setPlacement(PICKED_UP);
            return new Response(true, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.PICK_UP, Article.INDEFINITE, label));
        }
    };
    public Response pickUp(BasePickUpStrategy basePickUpStrategy, String label) {return null;}
}
