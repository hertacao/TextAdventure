package build;

import lombok.Getter;
import object.AbstractAdvObject;
import object.Door;
import object.Room;
import object.Scene;
import object.quality_interface.AdvObject;
import object.quality_interface.Connector;
import util.RelativeDirection;

import java.util.LinkedList;

@Getter
public class AdvObjectBuilder {
    private LinkedList<Scene> scenes = new LinkedList<>();
    private LinkedList<Connector> connectors = new LinkedList<>();

    public void build() {
        Room floor = new Room("floor");
        scenes.add(floor);
        Room livingroom = new Room("living room");
        scenes.add(livingroom);
        Room bathroom = new Room("bathroom");
        scenes.add(bathroom);
        Room bedroom = new Room("bedroom");
        scenes.add(bedroom);

        Door door_bath = new Door("DoorBathroom", floor, bathroom);
        connectors.add(door_bath);
        Door door_living = new Door("DoorLivingRoom", floor, livingroom);
        connectors.add(door_living);
        Door door_bed = new Door("DoorBedroom", livingroom, bedroom);
        connectors.add(door_bed);

        floor.addConnector(door_living, RelativeDirection.FRONT, livingroom);
        floor.addConnector(door_bath, RelativeDirection.LEFT, bathroom);

        bathroom.addConnector(door_bath, RelativeDirection.BACK, floor);

        livingroom.addConnector(door_living, RelativeDirection.BACK, floor);
        livingroom.addConnector(door_bed, RelativeDirection.RIGHT,bedroom);

        bedroom.addConnector(door_bed, RelativeDirection.LEFT, livingroom) ;

        //this.autoconnect();
    }

    public String toString() {
        StringBuilder output = new StringBuilder("scenes: ");
        output.append('\n');
        this.scenes.stream()
                .map(AdvObject::print)
                .forEach(o -> {output.append(o); output.append('\n');});
        output.append('\n');
        output.append("connectors: ");
        output.append('\n');
        this.connectors.stream()
                .map(AdvObject::print)
                .forEach(o -> {output.append(o); output.append('\n');});

        return output.toString();
    }

    /*
    private void autoconnect() {
        for(Connector c: connectors) {
            Scene s1 = c.getScene1();
            Scene s2 = c.getScene2();
            s1.addConnector(c, s2);
            s2.addConnector(c, s1);
        }
    }*/
}
