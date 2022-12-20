package dev.vitorpaulo.crosswordsresolver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CrossWords {

    private final List<List<String>> lines;
    private final ArrayList<Word> words;

    public String get(Integer index, Integer line, Direction direction) {
        return get(index + direction.getIndex(), line + direction.getLine());
    }

    public String get(Integer index, Integer line) {
        if (line < 0 || line >= lines.size()) {
            return null;
        }

        final var lineString = lines.get(line);
        if (index < 0 || index >= lineString.size()) {
            return null;
        }

        return lineString.get(index);
    }
}
