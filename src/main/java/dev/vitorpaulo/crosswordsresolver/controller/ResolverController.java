package dev.vitorpaulo.crosswordsresolver.controller;

import dev.vitorpaulo.crosswordsresolver.model.CrossWords;
import dev.vitorpaulo.crosswordsresolver.model.Word;
import dev.vitorpaulo.crosswordsresolver.service.ImageGeneratorService;
import dev.vitorpaulo.crosswordsresolver.service.ResolverService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/resolve")
public class ResolverController {

    private final ResolverService resolverService;

    @ResponseBody
    @PostMapping(value = "/image", produces = "image/png")
    public byte[] resolveCrossWordsAsImage() {
        return resolverService.resolveCrossWord(
                new CrossWords(
                        List.of(
                                (Arrays.stream("F O G C O N S C I O Q G O T M".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("C O M A O S O I V A M I D Y T".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("O V Z I Y R O O C I R T I C Q".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("t i u u n c d u y a k y a v c".toUpperCase().split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("O S A P A O O K R T R T L I M".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("V O N O M M R T O S T D M L A".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("I R T M C Z N C C U V O Z C X".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("A R I S F O V I A I R W U N D".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("G O V I C T S R O M V S G M C".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("J C I V L Z R T A T T N H I O".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("C T R I R N C Ç U I I G I M M".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("W A U C B D O B C R K C E U U".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("V R S W B K L O K D A B O R T".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("A C I N T O S O N D H O Y U A".split(" ")).collect(Collectors.toList())),
                                (Arrays.stream("B O C I N O N A C P J Z X C R".split(" ")).collect(Collectors.toList()))
                        ),
                        new ArrayList<Word>() {{
                            add(new Word("CONSCIO"));
                            add(new Word("ACINTOSO"));
                            add(new Word("DIAL"));
                            add(new Word("ATUM"));
                        }}
                )
        );
    }
}
