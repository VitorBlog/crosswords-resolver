package dev.vitorpaulo.crosswordsresolver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Word {

    private final String string;

    private Integer firstLetterPosition;
    private Integer firstLinePosition;

    private Integer lastLetterPosition;
    private Integer lastLinePosition;

    public String getFirstLetter() {
        return string.charAt(0) + "";
    }

    public String getByIndex(Integer index) {
        return string.charAt(index) + "";
    }

    public String getNext(String character) {
        final var index = string.indexOf(character) + 1;
        if (index > string.length()) {
            return null;
        }

        return string.charAt(index) + "";
    }
}
