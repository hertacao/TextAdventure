package commands;

import lombok.NonNull;
import objects.quality_interface.AdvObject;
import build.Game;
import process.Response;

public enum Action2P implements TwoPredicateAction {
    USE {
        @Override
        public String toString(){
            return "use";
        }
        public String pos_output() { return "You use the "; }
        public String neg_output() { return "You can't use the "; }
        public Response exec(Game game, @NonNull AdvObject o1, @NonNull AdvObject o2) {return null;}
    },
    GIVE {
        @Override
        public String toString() { return "give";}
        public String pos_output() { return "You give the "; }
        public String neg_output() { return "You can't give the "; }
        public Response exec(Game game, @NonNull AdvObject o1, @NonNull AdvObject o2) {return null;}
    },
    COMBINE {
        @Override
        public String toString(){
            return "combine";
        }
        public String pos_output() { return "You combine "; }
        public String neg_output() { return "You can't combine "; }
        public Response exec(Game game, @NonNull AdvObject o1, @NonNull AdvObject o2) {return null;}
    };

    //public String pos_output() {return null;}
    //public String neg_output() {return null;}
    //public Response exec(@NonNull AdvObject o, Game game) {return null;}
}
