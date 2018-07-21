package objects;

import lombok.Getter;
import lombok.Setter;
import objects.quality_interface.Connector;
import util.IDType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Herta on 18.01.2018.
 */
@Getter
@Setter
public class Door extends OpenCloseItem implements Connector {
    private Scene scene1;
    private Scene scene2;

    public Door(String name, String label, Scene scene1, Scene scene2, boolean closed, boolean locked) {
        super(name, label, closed, locked);
        this.scene1 = scene1;
        this.scene2 = scene2;
        this.definingIDType = IDType.DIRECTION;
    }

    public Door(String name, Scene scene1, Scene scene2) {
        super(name, "door", true, false);
        this.scene1 = scene1;
        this.scene2 = scene2;
        this.definingIDType = IDType.DIRECTION;
    }

    @Override
    public List<Scene> getScenes() {
        List<Scene> list = new LinkedList<>();
        list.add(scene1);
        list.add(scene2);
        return list;
    }
}
