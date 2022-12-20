package dev.vitorpaulo.crosswordsresolver.service;

import dev.vitorpaulo.crosswordsresolver.model.CrossWords;
import dev.vitorpaulo.crosswordsresolver.model.Direction;
import dev.vitorpaulo.crosswordsresolver.model.Word;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResolverService {

    private final ImageGeneratorService imageGeneratorService;

    public byte[] resolveCrossWord(CrossWords crossWords) {
        final var words = crossWords.getWords();

        int lineIndex = 0;
        for (List<String> line : crossWords.getLines()) {
            int index = 0;
            for (String letter : line) {
                final var possibleWords = words.stream().filter((word) -> word.getFirstLetter().equals(letter) && word.getFirstLetterPosition() == null);
                for (Word word : possibleWords.collect(Collectors.toList())) {
                    checkLetterDirections(crossWords, lineIndex, index, letter, word);
                }

                index++;
            }

            lineIndex++;
        }

        return imageGeneratorService.generateImage(crossWords);
    }

    private void checkLetterDirections(CrossWords crossWords, Integer line, Integer index, String letter, Word word) {
        for (Direction direction : Direction.values()) {
            final var wordResponse = checkLine(direction, crossWords, word, line, index);
            if (wordResponse != null) {
                System.out.println("-=- " + word.getString() + " found at " + direction + " direction -=-");
                crossWords.getWords().remove(word);
                crossWords.getWords().add(wordResponse);
                break;
            }
        }
    }

    private Word checkLine(Direction direction, CrossWords crossWords, Word word, Integer line, Integer index) {
        var actualIndex = index;
        var actualLine = line;

        for (int letterIndex = 1; letterIndex < word.getString().length(); letterIndex++) {
            final var letter = crossWords.get(actualIndex, actualLine, direction);

            if (letter == null || !letter.equals(word.getByIndex(letterIndex))) {
                return null;
            }

            actualIndex += direction.getIndex();
            actualLine += direction.getLine();
        }

        word.setFirstLinePosition(line);
        word.setFirstLetterPosition(index);
        word.setLastLinePosition(actualLine);
        word.setLastLetterPosition(actualIndex);
        return word;
    }

    private boolean checkNextLetter(CrossWords crossWords, Integer line, Integer index, String letter, Word word, Direction direction) {
        if (word.getFirstLetterPosition() == null) {
            word.setFirstLetterPosition(index);
            word.setFirstLinePosition(line);
        }

        var actualLetter = letter;
        var actualIndex = index;
        var actualLine = line;

        for (int letterIndex = 0; letterIndex < word.getString().length(); letterIndex++) {
            var nextLetter = crossWords.get(actualIndex, actualLine, direction);
            System.out.println(nextLetter + " = " + actualLetter +  " - l: " + actualLine + " i: " + actualIndex);

            if (nextLetter == null || !nextLetter.equals(word.getNext(actualLetter))) {
                return false;
            }

            actualLetter = nextLetter;
            actualIndex = index + direction.getIndex();
            actualLine = line + direction.getLine();

            word.setLastLetterPosition(actualIndex);
            word.setLastLinePosition(actualLine);
        }

        return true;
    }
}
