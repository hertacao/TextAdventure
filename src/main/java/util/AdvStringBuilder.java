package util;

import commands.Command;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import util.languages.Article;
import util.languages.Language;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class AdvStringBuilder {
    @Setter
    @Getter
    private static @NonNull Language language;

    public static String enumerate (String start,
                             @NonNull Collection<?> objects,
                             Map<?, ?> detail,
                             String end, Object... args ) {
        Article article = Article.INDEFINITE;
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

        if (detail != null) {
            for (Object o : objects) {
                output.append(", ");
                output.append(language.getArticle(o.toString(), article));
                output.append(" ");
                output.append(o.toString());
                output.append(" ");
                output.append(detail.get(o).toString());

            }
        } else {
            for (Object o : objects) {
                output.append(", ");
                output.append(language.getArticle(o.toString(), article));
                output.append(" ");
                output.append(o.toString());
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

    public static String enumerate (String start, @NonNull Collection<?> objects, String end, Object... args) {
        return enumerate(start,objects,null,end, args);
    }

    public static String enumerate (String start, @NonNull Collection<?> objects, Object... args) {
        return enumerate(start,objects,null,null, args);
    }

    public static String enumerate (@NonNull Collection<?> objects, String end, Object... args) {
        return enumerate(null,objects,null,end, args);
    }

    public static String enumerate (@NonNull Collection<?> objects, Object... args) {
        return enumerate(null,objects,null,null, args);
    }

    public static String enumerateCommand (String start, @NonNull Collection<? extends Command> command, String end) {
        java.lang.StringBuilder output = new java.lang.StringBuilder(start);
        for (Command c : command) {
            output.append(c.toString());
            output.append(", ");
        }
        output.append(". ");
        output.append(end);
        return output.toString();
    }

    public static String getArticle (String label, Article article_type) {
        return language.getArticle(label, article_type);
    }
}
