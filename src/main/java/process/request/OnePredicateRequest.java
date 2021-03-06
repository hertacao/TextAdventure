package process.request;

import build.Game;
import command.OnePredicateAction;
import lombok.ToString;
import object.interfaces.AdvObject;
import process.Response;

import java.util.List;

@ToString
public class OnePredicateRequest extends Request {
    private OnePredicateAction action;
    private AdvObject object;

    public OnePredicateRequest(List<String> input, OnePredicateAction action, AdvObject object) {
        super(input);
        this.action = action;
        this.object = object;
    }

    @Override
    public Response invoke(Game game, Object... args) {
        return this.action.exec(game, object);
    }
}
