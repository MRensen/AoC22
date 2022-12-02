package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import javax.crypto.spec.PSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Day02 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
//        input = Arrays.asList("A Y", "B X", "C Z");
        Map<String, Integer> compare = Map.of(
                "A X", 1 + 3,
                "A Y", 2 + 6,
                "A Z", 3 + 0,
                "B X", 1 + 0,
                "B Y", 2 + 3,
                "B Z",3 + 6,
                "C X",1 + 6,
                "C Y",2 + 0,
                "C Z", 3 + 3
        );
        return input.stream().mapToInt(compare::get).sum();
    }


    @Override
    public Integer part2(List<String> input) {
        Map<String, Integer> compare = Map.of(
                "A X", 3 + 0,
                "A Y", 1 + 3,
                "A Z", 2 + 6,
                "B X", 1 + 0,
                "B Y", 2 + 3,
                "B Z",3 + 6,
                "C X",2 + 0,
                "C Y",3 + 3,
                "C Z", 1 + 6
        );
        return input.stream().mapToInt(compare::get).sum();
    }
}
