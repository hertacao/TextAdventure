package language;

import lombok.Getter;
import process.parser.KeywordMatchStrategy;
import process.parser.StringTree;
import util.Token;
import util.Word;

import java.util.*;

public abstract class Language {
    @Getter protected Map<Token, List<String>> tokenMap;
    @Getter protected Map<Word, String> wordMap;
    @Getter protected Map<String, List<Token>> keywordMap = new LinkedHashMap<>();
    @Getter protected StringTree keywordTree;

    protected abstract Map<Token, List<String>> createToken();
    protected abstract Map<Word, String> createWords();
    public abstract String getArticle(String label, Article article_type);

    public Language() {
        this.createWords();
        this.createToken();
        this.createKeyword();
        this.keywordTree = new StringTree(keywordMap.keySet());
        this.keywordTree.setNodeMatchStrategy(new KeywordMatchStrategy(this));
    }

    private void createKeyword() {
        for (Map.Entry<Token, List<String>> t : this.tokenMap.entrySet()) {
            for (String string : t.getValue()) {
                if (this.keywordMap.containsKey(string)) {
                    this.keywordMap.get(string).add(t.getKey());
                } else {
                    this.keywordMap.put(string, Arrays.asList(t.getKey()));
                }
            }
        }
    }

    public boolean addToken (Token t, String... strings) {
        if (this.tokenMap.containsKey(t)) {
            this.tokenMap.get(t).addAll(Arrays.asList(strings));
            return true;
        } else {
            return false;
        }
    }
}
