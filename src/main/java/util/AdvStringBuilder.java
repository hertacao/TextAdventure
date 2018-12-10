package util;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import language.Article;
import language.Language;
import object.interfaces.AdvObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class AdvStringBuilder {
    @Setter
    @Getter
    private static @NonNull Language language;

    public static String getString (Object o) {
        return o.toString();
    }

    public static String getString (Word w) {
        return language.getWordMap().get(w);
    }

    public static String getString (Token t) {
        return language.getTokenMap().get(t).get(0);
    }

    // support for german is missing
    public static String getString (Article a) {
        return language.getArticle("", a);
    }

    private static String enumerate(Object... objects) {
        StringBuilder output = new StringBuilder();
        Arrays.stream(objects)
                .forEach(o -> {
                    if(o instanceof Iterable) {
                        output.append(AdvStringBuilder.enumerate(o));
                    } else if(o instanceof Token) {
                        output.append(AdvStringBuilder.getString((Token) o));
                    } else if (o instanceof Word) {
                        output.append(AdvStringBuilder.getString((Word) o));
                    } else if (o instanceof Article) {
                        output.append(AdvStringBuilder.getString((Article) o));
                    } else {
                        output.append(AdvStringBuilder.getString(o));
                    }
                    output.append(" ");
                });
        return output.toString();
    }

    public static String buildSentence (Object... objects) {
        StringBuilder output = new StringBuilder();
        output.append(AdvStringBuilder.enumerate(objects));
        output.setCharAt(0, Character.toUpperCase(output.charAt(0)));
        output.insert(output.length()-1, ".");
        return output.toString();
    }

    public static <T> String enumerate (String start,
                             @NonNull Collection<T> objects,
                             Map<T, ?> detail,
                             String end, Object... args ) {

        Article article;

        if(AdvObject.class.isAssignableFrom(objects.stream().findAny().getClass())) {
            article = Article.INDEFINITE;
        } else {
            article = Article.NONE;
        }

        if (args.length != 0 && args[0] instanceof Article) {
            article = (Article) args[0];
        }

        StringBuilder output;
        if (start != null) {
            output = new StringBuilder(start);
            output.append(" ");
        } else {
            output = new StringBuilder();
        }

        if (article == Article.NONE) {
            if(detail != null) {
                for (T o : objects) {
                    output.append(", ");
                    output.append(o.toString());
                    output.append(" ");
                    output.append(detail.get(o).toString());
                }
            } else {
                for (T o : objects) {
                    output.append(", ");
                    output.append(o.toString());
                }
            }
        } else {
            if (detail != null) {
                for (T o : objects) {
                    output.append(", ");
                    output.append(language.getArticle(o.toString(), article));
                    output.append(" ");
                    output.append(o.toString());
                    output.append(" ");
                    output.append(detail.get(o).toString());

                }
            } else {
                for (T o : objects) {
                    output.append(", ");
                    output.append(language.getArticle(o.toString(), article));
                    output.append(" ");
                    output.append(o.toString());
                }
            }
        }

        if(end == null || Character.isUpperCase(end.codePointAt(0))) {
            output.append(". ");
        } else {
            output.append(" ");
        }

        int i = output.indexOf(", ");
        output.delete(i, i+2);
        i = output.lastIndexOf(", ");
        if(i >= 0) { output.replace(i, i+2, " and "); }

        if (end != null) {
            output.append(end);
        }

        return output.toString();
    }

    public static <T> String enumerate (String start, @NonNull Collection<T> objects, String end, Object... args) {
        return enumerate(start,objects,null,end, args);
    }

    public static <T> String enumerate (String start, @NonNull Collection<T> objects, Object... args) {
        return enumerate(start,objects,null,null, args);
    }

    public static <T> String enumerate (@NonNull Collection<T> objects, String end, Object... args) {
        return enumerate(null,objects,null,end, args);
    }

    public static <T> String enumerate (@NonNull Collection<T> objects, Object... args) {
        return enumerate(null,objects,null,null, args);
    }
}
