package commands;

import commands.Command;

/**
 * Created by Herta on 18.01.2018.
 */
public enum Action implements Command {
    LOOK {
        @Override
        public String toString(){
            return "look";
        }
    },
    EXAMINE {
        @Override
        public String toString(){
            return "examine";
        }
    },
    PICK_UP {
        @Override
        public String toString(){
            return "pick up";
        }
    },
    OPEN {
        @Override
        public String toString(){
            return "open";
        }
    },
    CLOSE {
        @Override
        public String toString(){
            return "close";
        }
    },
    PUSH {
        @Override
        public String toString(){
            return "push";
        }
    },
    PULL {
        @Override
        public String toString(){
            return "pull";
        }
    },
    GO {
        @Override
        public String toString() { return "go"; }
    },
    USE {
        @Override
        public String toString(){
            return "use";
        }
    },
    GIVE {
        @Override
        public String toString() { return "give";}
    },
    TALK {
        @Override
        public String toString(){
            return "talk";
        }
    },
    COMBINE {
        @Override
        public String toString(){
            return "combine";
        }
    }
}
