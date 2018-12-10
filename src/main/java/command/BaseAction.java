package command;

import build.Game;
import lombok.NonNull;
import object.Item;
import object.Scene;
import object.action_inferface.*;
import object.interfaces.AdvObject;
import object.Container;
import process.Response;
import util.AdvStringBuilder;

/**
 * Created by Herta on 18.01.2018.
 */
public enum BaseAction implements OnePredicateAction {
    LOOK {
        public Response exec(Game game, @NonNull AdvObject o) {
            Response response = o.look();
            if (response.isSuccess() && o instanceof Container) {
                game.getDiscovered().addAll(((Container) o).getContents());
                if (o instanceof Scene) {
                    game.getDiscovered().addAll(((Scene) o).getSceneMap().keySet());
                }
            }
            return response;
        }
    },
    EXAMINE {
        public Response exec(Game game, @NonNull AdvObject o) {
            Response response = o.examine();
            if (response.isSuccess() && o instanceof Container) {
                game.getDiscovered().addAll(((Container) o).getContents());
            }
            return response;
        }
    },
    GO {
        public Response exec(Game game, @NonNull AdvObject o) {
            Response response = o.go();
            if (response.isSuccess() && o instanceof Scene) {
                game.addDiscovered(o);
                game.getReachableContainer().remove(game.getLocation());
                game.setLocation((Scene) o);
                game.addReachableContainer((Container) o);
            }
            return response;
        }
    },
    PICK_UP {
        public Response exec(Game game, @NonNull AdvObject o) {
            Response response = o.pickUp();
                if (response.isSuccess()) {
                    game.getInventory().add((Item) o);
                    ((Item) o).getHome().getContents().remove(o);
                }
            return response;
        }
    },
    OPEN {
        public Response exec(Game game, @NonNull AdvObject o) {
            Response response = o.open();
            if (response.isSuccess() && o instanceof Container) {
                game.getReachableContainer().add((Container) o);
            }
            return response;
        }
    },
    CLOSE {
        public Response exec(Game game, @NonNull AdvObject o) {
            Response response = o.close();
            if (response.isSuccess() && o instanceof Container) {
                game.getReachableContainer().remove(o);
            }
            return response;
        }
    },
    PUSH {
        public Response exec(Game game, @NonNull AdvObject o) {
            return o.push();
        }
    },
    PULL {
        public Response exec(Game game, @NonNull AdvObject o) {
            return o.pull();
        }
    },
    TALK {
        public Response exec(Game game, @NonNull AdvObject o) {return o.talk();}
    };

    public String toString(){ return AdvStringBuilder.getString(this); }
}
