package process.parser;

import util.Token;

import java.util.List;

public interface NodeMatchStrategy {
    boolean checkNode(StringNode node, List<String> input);
    List<Token> getToken();
}
