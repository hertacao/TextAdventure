package command;

import lombok.NonNull;
import object.interfaces.AdvObject;
import process.Response;
import build.Game;

public interface TwoPredicateAction extends Action {
    Response exec(Game game, @NonNull AdvObject o1, @NonNull AdvObject o2);
}
