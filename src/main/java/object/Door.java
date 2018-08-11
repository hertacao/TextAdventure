package object;

import lombok.Getter;
import lombok.Setter;
import object.quality_interface.Connector;
import object.quality_interface.Lockable;
import util.IDType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Herta on 18.01.2018.
 */
@Getter
@Setter
public class Door extends OpenCloseItem implements Connector, Lockable {
    private Scene scene1;
    private Scene scene2;

    public Door(String name, String label, Scene scene1, Scene scene2, boolean closed, boolean locked) {
        super(name, label, closed, locked);
        this.scene1 = scene1;
        this.scene2 = scene2;
        this.definingIDType = IDType.DIRECTION;
        this.reference.add("door");
    }

    public Door(String name, Scene scene1, Scene scene2, boolean closed, boolean locked) {
        this(name, "door", scene1, scene2, closed, locked);
    }

    public Door(String name, Scene scene1, Scene scene2) {
        this(name, "door", scene1, scene2, true, false);
    }


    @Override
    public List<Scene> getScenes() {
        List<Scene> list = new LinkedList<>();
        list.add(scene1);
        list.add(scene2);
        return list;
    }
}
