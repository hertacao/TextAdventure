package process.request;

import build.Game;
import command.Action;
import lombok.NonNull;
import lombok.ToString;
import process.Response;
import util.AdvStringBuilder;
import language.Article;

import java.util.List;
import java.util.Set;

@ToString
public class InvalidUnclearLabelRequest extends Request{
    @NonNull private Action action;
    @NonNull private Set<String> label;

    public InvalidUnclearLabelRequest(List<String> input, Action action, Set<String> label) {
        super(input);
        this.action = action;
        this.label = label;
    }

    @Override
    public Response invoke(Game game, Object... args) {
        return new Response(AdvStringBuilder.enumerate("Please specify", label,
                "you like to " + this.action.toString() + ".", Article.RELATIVE_PRONOUN), false);
    }
}
