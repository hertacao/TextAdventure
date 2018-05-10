package util;

import lombok.Getter;
import lombok.Setter;
import objects.AdvObject;
import objects.Item;
import objects.Scene;

import java.util.HashSet;

/**
 * Created by Herta on 18.01.2018.
 */
@Getter
@Setter
public class Game {
    private HashSet<Item> inventory;
    private HashSet<AdvObject> discovered;
    private HashSet<AdvObject> reachable;
    private Scene location;

    public Game(Scene start) {
        this.inventory = new HashSet<>();
        this.discovered = new HashSet<>();
        this.reachable = new HashSet<>();
        this.location = start;
        discovered.add(start);
        reachable.add(start);
    }
}
