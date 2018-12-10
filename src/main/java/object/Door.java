package object;

import lombok.Getter;
import lombok.Setter;
import object.interfaces.Connector;

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

    public Door(String name, String label, Scene scene1, Scene scene2) {
        super(name, label);
        this.scene1 = scene1;
        this.scene2 = scene2;
        this.reference.add("door");
    }

    public Door(String name, Scene scene1, Scene scene2) {
        this(name, name, scene1, scene2);
    }

    @Override
    public boolean isPassable() {
        return this.isOpen();
    }

    @Override
    public List<Scene> getScenes() {
        List<Scene> list = new LinkedList<>();
        list.add(scene1);
        list.add(scene2);
        return list;
    }
}
