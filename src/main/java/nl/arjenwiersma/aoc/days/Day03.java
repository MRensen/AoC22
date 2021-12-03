package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day03 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
        var instructionsArray = input.stream()
                .map(e->e.split(""))
                .toList();
        List<int[]>instructionsList = new ArrayList<>();
        for(String[] strings: instructionsArray) {
            int[] ints = Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
            instructionsList.add(ints);
        }
        int[] ones = new int[instructionsList.get(1).length];
        int[] zeroes = new int[instructionsList.get(1).length];

        for(int[] ints : instructionsList){
            for(int i = 0; i < ints.length; i++){
                if(ints[i] == 0){
                    zeroes[i] ++;
                } else {
                    System.out.println("1 = " + ints[i]);
                    ones[i] ++;
                }

            }
        }
        int[] gamma = new int[instructionsList.get(1).length];
        int[] epsilon = new int[instructionsList.get(1).length];

        for(int i = 0; i < ones.length; i++){
            int j = ones[i];
            int k = zeroes[i];
                if(j>k){
                    gamma[i] = 1;
                    epsilon[i] = 0;
                }
                if(k>j) {
                    gamma[i] = 0;
                    epsilon[i] = 1;
                }
            }
        return null;
        //3437
        //658
        }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
