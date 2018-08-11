package command;

import build.Game;
import lombok.NonNull;
import object.Door;
import object.Scene;
import object.action_inferface.Pullable;
import object.action_inferface.Pushable;
import object.quality_interface.AdvObject;
import process.Response;
import util.AdvStringBuilder;

import java.util.List;

public enum Routine implements OnePredicateAction {
    ENTER {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Door) {
                // Check if door is already open
                if (!((Door) o).isClosed()) {
                    List<Scene> scene = ((Door) o).getScenes();
                    scene.remove(game.getLocation());
                    BaseAction.GO.exec(game, scene.get(0));
                    return o.respondPositive(Routine.ENTER);
                }
                Response response = BaseAction.OPEN.exec(game, o);
                if (!((Door) o).isClosed()) {
                    List<Scene> scene = ((Door) o).getScenes();
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
            if (o instanceof Pullable) {
                return BaseAction.PULL.exec(game, o);
            } else if (o instanceof Pushable) {
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
