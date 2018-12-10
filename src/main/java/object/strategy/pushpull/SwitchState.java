package object.strategy.pushpull;

import command.BaseAction;
import language.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

@AllArgsConstructor
public class SwitchState {
    @Getter private String name;

    public Response push(String label) {
        return new Response(true, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.PUSH, Article.DEFINITE, label));
    }
}
