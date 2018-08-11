package language;

import lombok.Getter;
import util.Token;
import util.Word;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Language {
    @Getter protected Map<Token, List<String>> token = createToken();
    @Getter protected Map<Word, String> strings = createStrings();

    protected abstract Map<Token, List<String>> createToken();
    protected abstract Map<Word, String> createStrings();
    public abstract String getArticle(String label, Article article_type);

    public boolean addToken (Token t, String... strings) {
        if (token.containsKey(t)) {
            token.get(t).addAll(Arrays.asList(strings));
            return true;
        } else {
            return false;
        }
    }
}
