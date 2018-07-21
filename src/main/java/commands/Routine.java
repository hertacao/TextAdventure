package commands;

import build.Game;
import lombok.NonNull;
import objects.Door;
import objects.Scene;
import objects.action_inferface.Pullable;
import objects.action_inferface.Pushable;
import objects.quality_interface.AdvObject;
import process.Response;

import java.util.List;

public enum Routine implements OnePredicateAction {
    ENTER {
        @Override
        public String toString() { return "enter"; }
        public String pos_output() { return "You enter the "; }
        public String neg_output() { return "You can't enter the "; }
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
        @Override
        public String toString() { return "move"; }
        public String pos_output() { return "You move the "; }
        public String neg_output() { return "You can't move the "; }
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Pullable) {
                return BaseAction.PULL.exec(game, o);
            } else if (o instanceof Pushable) {
                return BaseAction.PUSH.exec(game, o);
            } else {
                return o.respondNegative(Routine.MOVE);
            }
        }
    }
}
