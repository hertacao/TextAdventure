package command;

import process.Response;
import build.Game;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Herta on 18.01.2018.
 */
public enum BaseControl implements Control {
    HELP {
        @Override
        public String toString(){
            return "help";
        }
        public Response exec(Game game, boolean yes) {  return null; }//return "This is the help menu";}
    },
    EXIT {
        @Override
        public String toString() { return "exit"; }
        public Response exec(Game game, boolean yes) { return game.respondExit(yes); }
    },
    HINT {
        @Override
        public String toString() { return "hint"; }
        public Response exec(Game game, boolean yes) {return null;}
    };

    private static Map< String, Command > map = new TreeMap< String, Command >();

    static {
        for (BaseControl c : values()) {
            map.put(c.toString(), c);
        }
    }

    public static Command getCommand(BaseControl control) {
        return (Command) control;
    }
}
