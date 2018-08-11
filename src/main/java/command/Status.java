package command;

import build.Game;
import process.Response;
import util.AdvStringBuilder;

public enum Status implements Control {
    LOCATION {
        public String toString() {return "location"; }
        // acceptable word: location, where am i, current room, status
        public Response exec(Game game, boolean yes) {
            return game.respondLocation();
            }
    },
    INVENTORY {
        // acceptable word: inventory, bag, carry, status
        public Response exec(Game game, boolean yes) {
            return game.respondInventory();
        }
    };

    public String toString(){ return AdvStringBuilder.getString(this); }

}
