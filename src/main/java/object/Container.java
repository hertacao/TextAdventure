package object;

import lombok.Getter;
import object.action_inferface.Pickable;
import process.parser.ObjectNameMatchStrategy;
import process.parser.StringTree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Container is defined as something containing other Items
 * Created by Herta on 22.01.2018.
 */
public abstract class Container extends AbstractAdvObject {
    // Contained items, like keys, ..
    @Getter protected Set<Item> contents = new HashSet<>();
    @Getter protected StringTree contentTree = new StringTree();
    @Getter protected Map<String, List<Item>> contentNameMap = new LinkedHashMap<>();
    public abstract boolean isContentAccessible();

    public Container(String name, String label) {
        super(name, label);
        this.contentTree.setNodeMatchStrategy(new ObjectNameMatchStrategy(this));
    }

    private void createContentNameMap() {
        for (Item item: this.contents) {
            for (String name : item.getReference()) {
                if (this.contentNameMap.containsKey(name)) {
                    this.contentNameMap.get(name).add(item);
                } else {
                    this.contentNameMap.put(name, Arrays.asList(item));
                }
            }
        }
    }

    public Set<Container> getContainer() {
        Set<Item> containers = this.contents.stream()
                .filter(item -> Container.class.isAssignableFrom(item.getClass()))
                .collect(Collectors.toSet());
        containers.stream()
                .filter(Container::isContentAccessible)
                .map(Container::getContainer)
                .forEach(containers::addAll);

        return containers;
    }

    public void addContent (Item... items) {
        this.contents.addAll(Arrays.asList(items));
        Arrays.stream(items)
                .forEach(item -> item.setHome(this));
        this.createContentNameMap();
        this.contentTree.addAll(contentNameMap.keySet());
    }

    public void removeContent (Item item) {
        this.contents.remove(item);
        List<String> names = new ArrayList<>();
        for(String name : item.getReference()) {
            if(contentNameMap.get(name).size() == 1) {
                contentNameMap.remove(name);
                names.add(name);
            } else {
                contentNameMap.get(name).remove(item);
            }
        }
        names.forEach(this.contentTree::remove);
    }

}
