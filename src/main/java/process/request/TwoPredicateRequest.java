package process.request;

import build.Game;
import command.TwoPredicateAction;
import lombok.ToString;
import object.interfaces.AdvObject;
import process.Response;

import java.util.List;

@ToString
public class TwoPredicateRequest extends Request{
    private TwoPredicateAction action;
    private AdvObject object1;
    private AdvObject object2;

    public TwoPredicateRequest(List<String> input, TwoPredicateAction action, AdvObject object1, AdvObject object2) {
        super(input);
        this.action = action;
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public Response invoke(Game game, Object... args) {
        return this.action.exec(game, object1, object2);
    }
}
