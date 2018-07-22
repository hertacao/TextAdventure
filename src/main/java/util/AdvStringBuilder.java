package util;

import command.Command;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import language.Article;
import language.Language;
import object.quality_interface.AdvObject;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class AdvStringBuilder {
    @Setter
    @Getter
    private static @NonNull Language language;

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

        java.lang.StringBuilder output;
        if (start != null) {
            output = new java.lang.StringBuilder(start);
            output.append(" ");
        } else {
            output = new java.lang.StringBuilder();
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
