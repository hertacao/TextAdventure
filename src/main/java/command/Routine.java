package command;

import build.Game;
import lombok.NonNull;
import object.Scene;
import object.action_inferface.Pullable;
import object.action_inferface.Pushable;
import object.interfaces.AdvObject;
import object.interfaces.Connector;
import process.Response;
import util.AdvStringBuilder;

import java.util.List;

/**
 * The idea of routines is a sequence of actions that are often performed after another.
 */
public enum Routine implements OnePredicateAction {
    ENTER {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Connector) {
                // Check if connector is already passable
                if (((Connector) o).isPassable()) {
                    List<Scene> scene = ((Connector) o).getScenes();
                    scene.remove(game.getLocation());
                    BaseAction.GO.exec(game, scene.get(0));
                    return o.respondPositive(Routine.ENTER);
                }
                Response response = BaseAction.OPEN.exec(game, o);
                if (((Connector) o).isPassable()) {
                    List<Scene> scene = ((Connector) o).getScenes();
                    scene.remove(game.getLocation());
                    BaseAction.GO.exec(game, scene.get(0));
                    return o.respondPositive(Routine.ENTER, "You open the door and enter the room. ");
                }
                return response;
            } else {
                return o.respondNegative(Routine.ENTER);
            }
        }
    },
    MOVE {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o.canExecute(BaseAction.PULL)) {
                return BaseAction.PULL.exec(game, o);
            } else if (o.canExecute(BaseAction.PUSH)) {
                return BaseAction.PUSH.exec(game, o);
            } else if (o instanceof Scene) {
                return BaseAction.GO.exec(game, o);
            } else {
                return o.respondNegative(Routine.MOVE);
            }
        }
    },
    LOOK_AROUND {
        public Response exec(Game game, @NonNull AdvObject o) {
            return BaseAction.LOOK.exec(game, game.getLocation());
        }
    };

    public String toString(){ return AdvStringBuilder.getString(this); }

}
