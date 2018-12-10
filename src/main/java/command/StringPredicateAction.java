package command;

import build.Game;
import lombok.NonNull;
import object.interfaces.AdvObject;
import process.Response;

public interface StringPredicateAction extends Action{
    Response exec(Game game, @NonNull AdvObject o, @NonNull String string);
}
