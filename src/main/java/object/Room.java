package object;

public class Room extends Scene{

    public Room (String name) {
        this(name, name);
    }

    public Room(String name, String label) {
        super(name, label);
        this.reference.add("room");
    }
}
