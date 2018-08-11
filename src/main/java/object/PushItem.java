package object;

import command.BaseAction;
import language.Article;
import lombok.NonNull;
import object.action_inferface.Pushable;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

import java.util.List;
import java.util.Set;

/**
 * Created by Herta on 10.05.2018.
 */
public abstract class PushItem extends Item implements Pushable {
    protected List<String> positions;
    protected Set<String> reachables;
    protected String currentPosition;
    protected int currentIndex;

    public PushItem(
            @NonNull String name,
            @NonNull String label,
            @NonNull List<String> positions,
            @NonNull Set<String> reachables,
            @NonNull String currentPosition) {
        super(name, label);
        this.positions = positions;
        this.reachables = reachables;
        this.currentPosition = currentPosition;
        this.currentIndex = this.positions.indexOf(currentPosition);
        this.executable.add(BaseAction.PUSH);
        this.executable.add(BaseAction.PULL);
    }

    @Override
    public Response push() {
        if(this.reachables.contains(this.positions.get(this.currentIndex + 1)) && this.executable.contains(BaseAction.PUSH)) {
            this.currentIndex++;
            this.currentPosition = this.positions.get(this.currentIndex);
            this.response.setSuccess(true);
            this.respondPositive(BaseAction.PUSH, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.PUSH, Article.DEFINITE, this.getLabel(), " to ", currentPosition));
        } else {
            this.respondNegative(BaseAction.PUSH);
        }
        return this.response;
    }
}
