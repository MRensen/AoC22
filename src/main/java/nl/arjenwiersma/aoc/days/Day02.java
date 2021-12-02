package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;

import java.util.Arrays;
import java.util.List;

public class Day02 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
        var instructionsArray = input.stream().map(e->e.split(" ")).toList();
        //System.out.println(input);
        int horizontal = 0;
        int depth = 0;

        for(var instructions : instructionsArray){
            String action = instructions[0];
            int value = Integer.parseInt(instructions[1]);
            switch (action) {
                case "forward" -> horizontal += value;
                case "up" -> depth -= value;
                case "down" -> depth += value;
            }

        }
        return horizontal * depth;
    }

    @Override
    public Integer part2(List<String> input) {
        var instructionsArray = input.stream().map(e->e.split(" ")).toList();

        int aim = 0;
        int horizontal = 0;
        int depth = 0;

        for(var instructions : instructionsArray){
            String action = instructions[0];
            int value = Integer.parseInt(instructions[1]);
            switch (action) {
                case "forward":
                    horizontal += value;
                    depth += (aim * value);
                    break;
                case "up" :
                    aim -= value;
                    break;
                case "down":
                    aim += value;
                    break;
            }

        }
        return horizontal * depth;
    }
}
