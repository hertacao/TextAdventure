package process.parser;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StringNode extends TreeNode<String> {
    private List<StringNode> shadows = new ArrayList<>();

    public StringNode(String string) {
        super(string);
    }

    private ShadowStringNode createShadow() {
        ShadowStringNode shadowNode = new ShadowStringNode(this);
        this.shadows.add(shadowNode);
        return shadowNode;
    }

    @Override
    public boolean isLeaf() {
        return super.isLeaf() & !(this instanceof ShadowStringNode);
    }

    @Override
    public List<TreeNode<String>> getChildren() {
        if (this instanceof ShadowStringNode) {
            return ((ShadowStringNode) this).getOriginal().getChildren();
        } else {
            return super.getChildren();
        }
    }

    public static StringNode convertFromTreeNode(TreeNode<String> treeNode) {
        return (StringNode) treeNode;
    }

    public static List<StringNode> convertFromTreeNode(List<TreeNode<String>> treeNodeList) {
        return treeNodeList.stream().map(StringNode::convertFromTreeNode).collect(Collectors.toList());
    }

    public List<StringNode> getChildrenAsStringNode() {
        return StringNode.convertFromTreeNode(this.getChildren());
    }

    public StringNode getParentAsStringNode() {
        return StringNode.convertFromTreeNode(this.getParent());
    }

    public List<String> toStringList() {
        return Arrays.asList(this.getData().split(" "));
    }

    public boolean isChildStringOf(StringNode node) {
        return this.toStringList().containsAll(node.toStringList());
    }

    public boolean isParentStringOf(StringNode node) {
        return node.toStringList().containsAll(this.toStringList());
    }

    public boolean isSiblingStringOf(StringNode node) {
        return this.isChildStringOf(node.getParentAsStringNode());
    }

    public StringNode registerChild (StringNode childNode) {
        if(childNode.getParentAsStringNode() == null || this.isSiblingStringOf(childNode)) {
            super.registerChild(childNode);
        } else {
            ShadowStringNode shadowNode = childNode.createShadow();
            super.registerChild(shadowNode);
        }
        return childNode;
    }

    public void registerChildren (List<StringNode> childNodeList) {
        childNodeList.forEach(this::registerChild);
    }

    public class ShadowStringNode extends StringNode{
        @Getter StringNode original;

        private ShadowStringNode(StringNode node) {
            super(node.getData());
            this.original = node;

        }
    }
}
