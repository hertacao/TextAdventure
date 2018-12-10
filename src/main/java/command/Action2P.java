package command;

import build.Game;
import lombok.NonNull;
import object.interfaces.AdvObject;
import process.Response;
import util.AdvStringBuilder;

public enum Action2P implements TwoPredicateAction {
    USE {
        public Response exec(Game game, @NonNull AdvObject o1, @NonNull AdvObject o2) {return null;}
    },
    GIVE {
        public Response exec(Game game, @NonNull AdvObject o1, @NonNull AdvObject o2) {return null;}
    },
    COMBINE {
        public Response exec(Game game, @NonNull AdvObject o1, @NonNull AdvObject o2) {return null;}
    };

    public String toString(){ return AdvStringBuilder.getString(this); }
}
