package process.parser;

import lombok.Getter;
import lombok.Setter;
import util.Token;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class StringTree {
    private StringNode root = new StringNode("");
    private Map<String, StringNode> stringMap = new LinkedHashMap<>();
    @Setter private NodeMatchStrategy nodeMatchStrategy;

    public StringTree() {    }

    public StringTree(Iterable<String> iterable) {
        iterable.forEach(this::add);
    }

    public StringNode add(String string) {
        if (stringMap.containsKey(string)) {
            return stringMap.get(string);
        } else {
            StringNode newNode = new StringNode(string);
            this.stringMap.put(string, newNode);
            checkNodeAndRegister(root, newNode);
            return newNode;
        }
    }

    public StringTree addAll(Iterable<String> iterable) {
        iterable.forEach(this::add);
        return this;
    }

    public StringTree remove(String string) {
        if (stringMap.containsKey(string)) {
            StringNode node = stringMap.get(string);
            node.getParentAsStringNode().registerChildren(node.getChildrenAsStringNode());
            node.children = null;
            stringMap.remove(string);
        }
        return this;
    }

    private void checkNodeAndRegister(StringNode node, StringNode newNode) {
        if (newNode.isChildStringOf(node)) {
            if (node.isLeaf()) {
                node.registerChild(newNode);
            } else {
                for (StringNode child : node.getChildrenAsStringNode()) {
                    checkNodeAndRegister(child, newNode);
                }
            }
        } else if (newNode.isParentStringOf(node)) {
            node.getParentAsStringNode().registerChild(newNode);
            newNode.registerChild(node);
        } else {
            node.getParentAsStringNode().registerChild(newNode);
        }
    }

    private boolean checkForLongestMatch(StringNode node, List<String> input) {
        if (node.isLeaf()) {
            return this.nodeMatchStrategy.checkNode(node,input);
        } else {
            boolean match = false;
            for (StringNode childNode : node.getChildrenAsStringNode()) {
                if(checkForLongestMatch(childNode, input)) {
                    match = true;
                }
            }
            if(!match) {
                return this.nodeMatchStrategy.checkNode(node,input);
            }

            return false;
        }
    }

    public List<Token> checkForMatch(List<String> input) {
        this.checkForLongestMatch(this.root, input);
        return this.nodeMatchStrategy.getToken();
    }
}
