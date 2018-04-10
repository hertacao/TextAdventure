package util;

import lombok.Getter;
import lombok.Setter;
import objects.Item;
import objects.Object;
import objects.Scene;

import java.util.HashSet;

/**
 * Created by Herta on 18.01.2018.
 */
@Getter
@Setter
public class Game {
    private HashSet<Item> inventory;
    private HashSet<Object> discovered;
    private HashSet<Object> reachable;
    private Scene location;

    public Game(Scene start) {
        this.inventory = new HashSet<Item>();
        this.discovered = new HashSet<Object>();
        this.reachable = new HashSet<Object>();
        this.location = start;
        discovered.add(start);
        reachable.add(start);
    }
}
