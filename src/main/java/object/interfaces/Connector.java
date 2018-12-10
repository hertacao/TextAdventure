package object.interfaces;

import object.Scene;

import java.util.List;

/**
 * A Connecter is defined as something connecting multiple scenes, implemented by Door, ...
 */
public interface Connector extends AdvObject {
    List<Scene> getScenes();
    boolean isPassable();
}
