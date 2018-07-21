package build;

import lombok.Getter;
import objects.Door;
import objects.Scene;
import objects.quality_interface.Connector;
import util.RelativeDirection;

import java.util.LinkedList;

@Getter
public class AdvObjectBuilder {
    private LinkedList<Scene> scenes = new LinkedList<>();
    private LinkedList<Connector> connectors = new LinkedList<>();

    public void build() {
        Scene floor = new Scene("floor");
        scenes.add(floor);
        Scene livingroom = new Scene("living room");
        scenes.add(livingroom);
        Scene bathroom = new Scene("bathroom");
        scenes.add(bathroom);
        Scene bedroom = new Scene("bedroom");
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
