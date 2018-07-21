package util.languages;

import util.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class German extends Language{
    private Map<String, String> indef_articles = new HashMap<>();
    private Map<String, String> def_articles = new HashMap<>();
    private Map<String, String> relpron_articles = new HashMap<>();
    private Map<String, Token> token = new HashMap<>();

    @Override
    protected Map<Token, List<String>> createMap() {
        // please complete
        return null;
    }

    public String getArticle(String label, Article article_type) {
        switch(article_type) {
            case DEFINITE: return def_articles.get(label);
            case INDEFINITE: return indef_articles.get(label);
            case RELATIVE_PRONOUN: return relpron_articles.get(label);
            case NONE: return "";
        }
        return null;
    }

    public void addArticle(String article, String label, Article article_type) {
        switch(article_type) {
            case DEFINITE: def_articles.put(label, article);
            case INDEFINITE: indef_articles.put(label, article);
            case RELATIVE_PRONOUN: relpron_articles.put(label, article);
        }
    }
}
