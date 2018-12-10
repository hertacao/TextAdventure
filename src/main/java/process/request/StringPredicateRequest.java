package process.request;

import build.Game;
import command.StringPredicateAction;
import lombok.ToString;
import object.interfaces.AdvObject;
import process.Response;

import java.util.List;

@ToString
public class StringPredicateRequest extends Request{
    private StringPredicateAction action;
    private AdvObject object;
    private String string;

    public StringPredicateRequest(List<String> input, StringPredicateAction action, AdvObject object, String string) {
        super(input);
        this.action = action;
        this.object = object;
        this.string = string;
    }

    @Override
    public Response invoke(Game game, Object... args) {
        return this.action.exec(game, object, string);
    }
}
