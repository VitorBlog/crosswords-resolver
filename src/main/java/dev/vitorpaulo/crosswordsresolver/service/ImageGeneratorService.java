package dev.vitorpaulo.crosswordsresolver.service;

import dev.vitorpaulo.crosswordsresolver.model.CrossWords;
import dev.vitorpaulo.crosswordsresolver.model.Word;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ImageGeneratorService {

    private static final Integer LETTER_SIZE = 32;

    private Integer lineAmount = 15;
    private Integer lineSize = 15;

    public byte[] generateImage(CrossWords crossWords) {
        calculateSize(crossWords);

        final var image = createImage();
        final var graphics = image.getGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setColor(Color.BLACK);

        int lineIndex = 0;
        for (List<String> line : crossWords.getLines()) {
            int index = 0;
            for (String letter : line) {
                graphics.drawString(letter, (index + 1) * LETTER_SIZE, (lineIndex + 1) * LETTER_SIZE);

                index++;
            }

            lineIndex++;
        }

        for (Word word : crossWords.getWords()) {
            if (word.getFirstLetterPosition() == null) {
                continue;
            }

            graphics.drawLine(
                    (word.getFirstLetterPosition() + 2) * 30,
                    (word.getFirstLinePosition() + 2) * 27,
                    (word.getLastLetterPosition() + 2) * 30,
                    (word.getLastLinePosition() + 2) * 27
            );
        }

        return convertToBytes(image);
    }

    private void calculateSize(CrossWords crossWords) {
        lineAmount = crossWords.getLines().size();
        lineSize = crossWords.getLines().get(0).size();
    }

    private BufferedImage createImage() {
        return new BufferedImage(LETTER_SIZE * (lineSize + 1), (lineAmount + 1) * LETTER_SIZE, BufferedImage.TYPE_INT_RGB);
    }

    @SneakyThrows
    private byte[] convertToBytes(BufferedImage image) {
        final var outputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }
}
