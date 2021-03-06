package object;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Herta on 05.05.2018.
 */
public class Lever extends PushPullItem {
    public Lever(int num_pos) {
        super("lever","lever", new ArrayList<>(), new HashSet<>(), "0");
        for (int i= 0; i>-num_pos; i--) {
            this.positions.add(Integer.toString(i));
            this.reachables.add(Integer.toString(i));
            this.reference.add("lever");
        }
    }

}
