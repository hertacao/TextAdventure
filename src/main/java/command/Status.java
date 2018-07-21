package command;

import process.Response;
import build.Game;

public enum Status implements Control {
    LOCATION {
        @Override
        public String toString() {return "location"; }
        // acceptable word: location, where am i, current room, status
        public Response exec(Game game, boolean yes) {
            return game.respondLocation();
            }
    },
    INVENTORY {
        @Override
        public String toString() {
            return "inventory";
        }

        // acceptable word: inventory, bag, carry, status
        public Response exec(Game game, boolean yes) {
            return game.respondInventory();
        }
    }
}
