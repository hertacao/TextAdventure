package language;

import lombok.Getter;
import util.Token;

import java.util.List;
import java.util.Map;

public abstract class Language {
    @Getter protected Map<Token, List<String>> token = createMap();

    protected abstract Map<Token, List<String>> createMap();
    public abstract String getArticle(String label, Article article_type);
}
