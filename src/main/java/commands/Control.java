package commands;

import commands.Command;

import java.util.LinkedList;

/**
 * Created by Herta on 18.01.2018.
 */
public enum Control implements Command {
    HELP {
        @Override
        public String toString(){
            return "help";
        }
        public String exec() {  return "This is the help menu";}
    },
    EXIT {
        @Override
        public String toString() { return "exit"; }
        public String exec() {  return "Are your sure you want to exit?";}
    },
    HINT {
        @Override
        public String toString() { return "hint"; }
    },
    LOCATION {
        @Override
        public String toString() {return "location"; }
        // acceptable word: location, where am i, current room, status
    },
    INVENTORY {
        @Override
        public String toString() {return "inventory"; }
        // acceptable word: inventory, bag, carry, status
    }
}
