package process.parser;

import lombok.Getter;

import object.Item;
import object.Container;
import util.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectNameMatchStrategy implements NodeMatchStrategy {
    private Container container;
    @Getter private List<Token> token = new ArrayList<>();

    public ObjectNameMatchStrategy(Container container) {
        this.container = container;
    }

    public boolean checkNode(StringNode node, List<String> input) {
        // checks for match
        if (Collections.indexOfSubList(input,node.toStringList()) != -1) {
            // obtains items associated with this string
            List<Item> itemList = this.container.getContentNameMap().get(node.getData());
            if (itemList.size() == 1) {
                this.token.add(itemList.get(0));
                return true;
            } else {
                // homonym handler
                return true;
            }
        } else {
            return false;
        }
    }
}
