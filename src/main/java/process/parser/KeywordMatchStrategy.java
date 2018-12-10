package process.parser;

import language.Language;
import lombok.Getter;
import util.Token;

import java.util.ArrayList;
import java.util.List;

public class KeywordMatchStrategy implements NodeMatchStrategy {
    private Language language;
    @Getter private List<Token> token = new ArrayList<>();

    public KeywordMatchStrategy(Language language) {
        this.language = language;
    }

    public boolean checkNode(StringNode node, List<String> input) {
        // checks for match
        if (input.containsAll(node.toStringList())) {
            // obtains Token associated with this string
            List<Token> tokenList = this.language.getKeywordMap().get(node.getData());
            if (tokenList.size() == 1) {
                this.token.add(tokenList.get(0));
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
