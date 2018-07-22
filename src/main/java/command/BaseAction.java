package command;

import lombok.NonNull;
import object.*;
import object.action_inferface.*;
import object.quality_interface.AdvObject;
import object.quality_interface.Connector;
import object.quality_interface.Container;
import build.Game;
import process.Response;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Herta on 18.01.2018.
 */
public enum BaseAction implements OnePredicateAction {
    LOOK {
        @Override
        public String toString(){ return "look"; }
        public String pos_output() { return "You look at the "; }
        public String neg_output() { return "You can't look at the "; }
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
        @Override
        public String toString(){
            return "examine";
        }
        public String pos_output() { return "You examine the "; }
        public String neg_output() { return "You can't examine the "; }

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
        @Override
        public String toString(){
            return "pick up";
        }
        public String pos_output() { return "You pick up a "; }
        public String neg_output() { return "You can't pick up a "; }
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
        @Override
        public String toString(){ return "open"; }
        public String pos_output() { return "You open the "; }
        public String neg_output() { return "You can't open the "; }
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
        @Override
        public String toString(){
            return "close";
        }
        public String pos_output() { return "You close the "; }
        public String neg_output() { return "You can't close the "; }
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
        @Override
        public String toString(){
            return "push";
        }
        public String pos_output() { return "You push the "; }
        public String neg_output() { return "You can't push the "; }
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Pushable) {
                return ((Pushable) o).push();
            } else {
                return o.respondNegative(BaseAction.PUSH);
            }
        }
    },
    PULL {
        @Override
        public String toString(){
            return "pull";
        }
        public String pos_output() { return "You pull the "; }
        public String neg_output() { return "You can't pull the"; }
        public Response exec(Game game, @NonNull AdvObject o) {
            if (o instanceof Pullable) {
                return ((Pullable) o).pull();
            } else {
                return o.respondNegative(BaseAction.PULL);
            }
        }
    },
    GO {
        @Override
        public String toString() { return "go"; }
        public String pos_output() { return "You go to the "; }
        public String neg_output() { return "You can't go to the "; }
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
        @Override
        public String toString() {
            return "talk";
        }
        public String pos_output() {
            return "You talk to ";
        }
        public String neg_output() {
            return "You can't talk to ";
        }
        public Response exec(Game game, @NonNull AdvObject o) {return null;}
    }
}
