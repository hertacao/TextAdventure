package command;

import build.Game;
import lombok.NonNull;
import object.Item;
import object.Scene;
import object.action_inferface.*;
import object.quality_interface.AdvObject;
import object.quality_interface.Connector;
import object.quality_interface.Container;
import process.Response;
import util.AdvStringBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Herta on 18.01.2018.
 */
public enum BaseAction implements OnePredicateAction {
    LOOK {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Lookable) {
                Response response = o.look();
                if (response.isSuccess() & o instanceof Container) {
                    game.getDiscovered().addAll(((Container) o).getContents());
                    game.getReachable().addAll(((Container) o).getContents());
                    if (o instanceof Scene) {
                        game.getDiscovered().addAll(((Scene) o).getLinks().keySet());
                        game.getReachable().addAll(((Scene) o).getLinks().keySet());
                    }
                }
                return response;
            } else {
                return o.respondNegative(BaseAction.LOOK);
            }

        }
    },
    EXAMINE {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Examinable) {
                Response response = o.examine();
                if (response.isSuccess() & o instanceof Container) {
                    game.getDiscovered().addAll(((Container) o).getContents());
                    game.getReachable().addAll(((Container) o).getContents());
                }
                return response;
            } else {
                return o.respondNegative(BaseAction.EXAMINE);
            }

        }
    },
    PICK_UP {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Pickable) {
                Response response = ((Pickable) o).pickUp();
                if (response.isSuccess()) {
                    game.getInventory().add((Item) o);
                    ((Pickable) o).getContainer().getContents().remove(o);
                }
                return response;
            } else {
                return o.respondNegative(BaseAction.PICK_UP);
            }
        }
    },
    OPEN {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Openable) {
                Response response = ((Openable) o).open();
                if (response.isSuccess() & o instanceof Container) {
                    game.getReachable().addAll(((Container) o).getContents());
                }
                if (response.isSuccess() & o instanceof Connector) {
                    game.getReachable().addAll(((Connector) o).getScenes());
                }
                return response;
            } else {
                return o.respondNegative(BaseAction.OPEN);
            }
        }
    },
    CLOSE {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Closable) {
                Response response = ((Openable) o).open();
                if (response.isSuccess() & o instanceof Container) {
                    game.getReachable().removeAll(((Container) o).getContents());
                }
                return response;
            } else {
                return o.respondNegative(BaseAction.CLOSE);
            }
        }
    },
    PUSH {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Pushable) {
                return ((Pushable) o).push();
            } else {
                return o.respondNegative(BaseAction.PUSH);
            }
        }
    },
    PULL {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Pullable) {
                return ((Pullable) o).pull();
            } else {
                return o.respondNegative(BaseAction.PULL);
            }
        }
    },
    GO {
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Goable) {
                Response response = ((Goable) o).go();
                if (response.isSuccess() & o instanceof Scene) {
                    game.addDiscovered(o);
                    game.setLocation((Scene) o);
                    game.getReachable().clear();
                    Set<AdvObject> content = new HashSet<>();
                    content.add(o);
                    content.addAll(((Scene) o).getContents());
                    content.addAll(((Scene) o).getLinks().keySet());
                    ((Scene) o).getLinks().forEach(
                            (connector, scene) -> {
                                if(!(connector instanceof Openable)) {
                                    content.add(scene);
                                } else if (!((Openable) connector).isClosed()) {
                                    content.add(scene);
                                }
                            });
                    content.retainAll(game.getDiscovered());
                    game.setReachable(content);
                }
                return response;
            } else {
                return o.respondNegative(BaseAction.GO);
            }
        }
    },
    TALK {
        public Response exec(Game game, @NonNull AdvObject o) {return null;}
    };

    public String toString(){ return AdvStringBuilder.getString(this); }
}
