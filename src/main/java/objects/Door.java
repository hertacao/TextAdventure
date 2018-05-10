package objects;

/**
 * Created by Herta on 18.01.2018.
 */
public class Door extends OpenCloseItem {
    private Scene s1;
    private Scene s2;

    public Door(String name, String label, boolean close, Scene s1, Scene s2) {
        super(name, label, close);
        this.s1 = s1;
        this.s2 = s2;
    }
}
