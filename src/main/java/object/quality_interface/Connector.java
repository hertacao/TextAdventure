package object.quality_interface;

import object.Scene;

import java.util.List;

/**
 * A Connecter is defined as something connecting two scenes, implemented by Door, ...
 */
public interface Connector extends AdvObject {
    List<Scene> getScenes();
}
