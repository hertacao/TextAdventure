package object.strategy.pushpull;

import command.BaseAction;
import language.Article;
import lombok.NonNull;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

import java.util.List;
import java.util.Set;

public class BasePushPullStrategy implements PushStrategy, PullStrategy {
    protected List<String> positions;
    protected Set<String> reachables;
    protected String currentPosition;
    protected int currentIndex;

    public BasePushPullStrategy(
            @NonNull List<String> positions,
            @NonNull Set<String> reachables,
            @NonNull String currentPosition) {
        this.positions = positions;
        this.reachables = reachables;
        this.currentPosition = currentPosition;
        this.currentIndex = this.positions.indexOf(currentPosition);
    }

    @Override
    public Response push(String label) {
        Response response;
        if(this.reachables.contains(this.positions.get(this.currentIndex + 1))) {
            this.currentIndex++;
            this.currentPosition = this.positions.get(this.currentIndex);
            response = new Response(true, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.PUSH, Article.DEFINITE, label, " to ", currentPosition));
        } else {
            response = new Response(true, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.PUSH, Article.DEFINITE, label));
        }
        return response;
    }

    @Override
    public Response pull(String label) {
        Response response;
        if(this.reachables.contains(this.positions.get(this.currentIndex - 1))) {
            this.currentIndex--;
            this.currentPosition = this.positions.get(this.currentIndex);
            response = new Response(true, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.PULL, Article.DEFINITE, label, " to ", currentPosition));
        } else {
            response = new Response(true, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.PULL, Article.DEFINITE, label));
        }
        return response;
    }
}
