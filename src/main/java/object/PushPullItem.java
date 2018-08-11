package object;

import command.BaseAction;
import language.Article;
import lombok.NonNull;
import object.action_inferface.Pullable;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

import java.util.List;
import java.util.Set;

/**
 * Created by Herta on 05.05.2018.
 */
public abstract class PushPullItem extends PushItem implements Pullable {

    public PushPullItem(
            @NonNull String name,
            @NonNull String label,
            @NonNull List<String> positions,
            @NonNull Set<String> reachables,
            @NonNull String currentPosition) {
        super(name, label, positions, reachables, currentPosition);
    }

    @Override
    public Response pull() {
        if(this.reachables.contains(this.positions.get(this.currentIndex - 1)) && this.executable.contains(BaseAction.PULL)) {
            this.currentIndex--;
            this.currentPosition = this.positions.get(this.currentIndex);
            this.respondPositive(BaseAction.PULL, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.PUSH, Article.DEFINITE, this.getLabel(), " to ", currentPosition));
        } else {
            this.respondNegative(BaseAction.PULL);
        }
        return this.response;
    }
}
