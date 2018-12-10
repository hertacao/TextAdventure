/*package process.parser;

import object.interfaces.AdvObject;
import util.IDType;
import util.Token;

import java.util.*;
import java.util.stream.Collectors;

public class HomonymHandler {

    private LinkedList<? extends Token> searchOther(List<String> input, IDType idType) {
        switch (idType) {
            case DIRECTION: {
                LinkedList<Direction> direction = new LinkedList<>();
                Arrays.stream(CardinalDirection.values()).filter(a -> input.contains(a.toString())).forEach(direction::add);
                Arrays.stream(RelativeDirection.values()).filter(a -> input.contains(a.toString())).forEach(direction::add);
                return direction;
            }
            default: return null;
        }
    }

    private Map<String, List<AdvObject>> checkObjectHomonym(Map<String, List<AdvObject>> objectMap) {
        if (predicate > 2 || predicate < 1) {
            // raise Exception;
        }

        Map<String, List<AdvObject>> label = object.stream()
                .collect(Collectors.groupingBy(AdvObject::getLabel));

        int max_ObjectPerLabel;
        if (label.size() == 1) {
            max_ObjectPerLabel = predicate;
        } else if (predicate == 2 && label.size() == 2) {
            max_ObjectPerLabel = 1;
        } else {
            return Collections.emptyMap();
        }

        for (Map.Entry<String, List<AdvObject>> entry : label.entrySet()) {
            Set<IDType> idType = entry.getValue().stream()
                    .collect(Collectors.groupingBy(AdvObject::getDefiningIDType))
                    .keySet();
            // special handling if the object are defined by directionMap, need to look for directions in Scene
            if (idType.size() == 1 && idType.contains(IDType.DIRECTION)) {
                // find if directionMap in input exists at current location
                List<AdvObject> connector = game.getLocation().getDirectionMap().entrySet().stream()
                        .filter(directionMap -> this.other.contains(directionMap.getValue()))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                if (connector.size() <= max_ObjectPerLabel) {
                    label.put(entry.getKey(), connector);
                } else {
                    label.put(entry.getKey(), Collections.emptyList());
                }
                // define object by their description, please complete
            } else {
                List<? extends Token> identifier = idType.stream()
                        .map(i -> this.searchOther(this.input, i))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                //return null;
            }
        }
        return label;
    }
}*/
