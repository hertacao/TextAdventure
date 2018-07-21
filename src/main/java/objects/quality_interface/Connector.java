package objects.quality_interface;

import objects.Scene;

import java.util.List;

/**
 * A Connecter is defined as something connecting two scenes, implemented by Door, ...
 */
public interface Connector extends AdvObject {
    List<Scene> getScenes();
}
