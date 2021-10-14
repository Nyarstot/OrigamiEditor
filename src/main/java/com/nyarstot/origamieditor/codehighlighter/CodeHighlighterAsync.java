package com.nyarstot.origamieditor.codehighlighter;

import com.nyarstot.origamieditor.editor.OrigamiTextArea;
import javafx.concurrent.Task;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeHighlighterAsync {
    // Private

    private Subscription subscription;
    private OrigamiTextArea linkedTextArea;
    private ExecutorService executor;

    private static Pattern highlightPattern;

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = linkedTextArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        this.linkedTextArea.getCodeArea().setStyleSpans(0, highlighting);
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = highlightPattern.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    // Public

    public CodeHighlighterAsync(OrigamiTextArea textArea, ExecutorService executorService) {
        this.linkedTextArea = textArea;
        this.executor = executorService;
    }

    public void loadHighlightPattern(Pattern pattern) {
        highlightPattern = pattern;
    }

    public void cleanUpWhenDone(OrigamiTextArea textArea) {
        this.subscription =textArea.getCodeArea().multiPlainChanges()
                .successionEnds(Duration.ofMillis(250))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(textArea.getCodeArea().multiPlainChanges())
                .filterMap(t -> {
                    if(t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::applyHighlighting);
    }

}
