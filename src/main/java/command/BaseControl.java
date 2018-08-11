package command;

import build.Game;
import process.Response;
import util.AdvStringBuilder;

/**
 * Created by Herta on 18.01.2018.
 */
public enum BaseControl implements Control {
    HELP {
        public Response exec(Game game, boolean yes) {  return null; }//return "This is the help menu";}
    },
    EXIT {
        public Response exec(Game game, boolean yes) { return game.respondExit(yes); }
    },
    HINT {
        public Response exec(Game game, boolean yes) {return null;}
    };

    public String toString(){ return AdvStringBuilder.getString(this); }
}
