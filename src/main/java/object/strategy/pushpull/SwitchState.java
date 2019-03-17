package object.strategy.pushpull;

import command.BaseAction;
import language.Article;
import lombok.*;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

@RequiredArgsConstructor
public class SwitchState {
    private final BasePushStrategy basePushStrategy;
    @NonNull
    @Getter private String name;
    @Setter private SwitchState next;

    public Response push(String label) {
        this.basePushStrategy.setSwitchState(this.next);
        return new Response(true, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.PUSH, Article.DEFINITE, label));
    }
}
