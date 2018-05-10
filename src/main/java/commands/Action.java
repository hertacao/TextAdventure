package commands;

import commands.Command;

/**
 * Created by Herta on 18.01.2018.
 */
public enum Action implements Command {
    LOOK {
        @Override
        public String toString(){ return "look"; }
        public String pos_output() { return "You look at the "; }
        public String neg_output() { return "You can't look at the "; }
    },
    EXAMINE {
        @Override
        public String toString(){
            return "examine";
        }
        public String pos_output() { return "You examine the "; }
        public String neg_output() { return "You can't examine the "; }
    },
    PICK_UP {
        @Override
        public String toString(){
            return "pick up";
        }
        public String pos_output() { return "You pick up a "; }
        public String neg_output() { return "You can't pick up a "; }
    },
    OPEN {
        @Override
        public String toString(){ return "open"; }
        public String pos_output() { return "You open the "; }
        public String neg_output() { return "You can't open the "; }
    },
    CLOSE {
        @Override
        public String toString(){
            return "close";
        }
        public String pos_output() { return "You close the "; }
        public String neg_output() { return "You can't close the "; }
    },
    PUSH {
        @Override
        public String toString(){
            return "push";
        }
        public String pos_output() { return "You push the "; }
        public String neg_output() { return "You can't push the "; }
    },
    PULL {
        @Override
        public String toString(){
            return "pull";
        }
        public String pos_output() { return "You pull the "; }
        public String neg_output() { return "You can't pull the"; }
    },
    GO {
        @Override
        public String toString() { return "go"; }
        public String pos_output() { return "You go to the "; }
        public String neg_output() { return "You can't go to the "; }
    },
    USE {
        @Override
        public String toString(){
            return "use";
        }
        public String pos_output() { return "You use the "; }
        public String neg_output() { return "You can't use the "; }
    },
    GIVE {
        @Override
        public String toString() { return "give";}
        public String pos_output() { return "You give the "; }
        public String neg_output() { return "You can't give the "; }
    },
    TALK {
        @Override
        public String toString(){
            return "talk";
        }
        public String pos_output() { return "You talk to "; }
        public String neg_output() { return "You can't talk to "; }
    },
    COMBINE {
        @Override
        public String toString(){
            return "combine";
        }
        public String pos_output() { return "You combine "; }
        public String neg_output() { return "You can't combine "; }
    };

    public String pos_output() {return null;}
    public String neg_output() {return null;}
}
