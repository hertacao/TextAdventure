package command;

import build.Game;
import lombok.NonNull;
import object.interfaces.AdvObject;
import process.Response;
import util.AdvStringBuilder;

public enum Action2PString implements StringPredicateAction{
    ENTER {
        public Response exec(Game game, @NonNull AdvObject o, @NonNull String string) {return null;}
    };

    public String toString(){ return AdvStringBuilder.getString(this); }
}
