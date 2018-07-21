package commands;

import lombok.NonNull;
import objects.quality_interface.AdvObject;
import process.Response;
import build.Game;

public interface OnePredicateAction extends Action{
    Response exec(Game game, @NonNull AdvObject o);
}
