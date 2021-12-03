package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;
import java.util.List;

public class Day03 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
        int length = input.get(0).length();

        int[] ones = new int[length];
        int[] zeroes = new int[length];

        for(String str: input){
            for(int i = 0; i < length; i++){
                if(str.charAt(i) == '0'){
                    zeroes[i] ++;
                } else {
                    ones[i] ++;
                }
            }
        }
        char[] gamma = new char[length];
        char[] epsilon = new char[length];

        for(int i = 0; i < length; i++){
            int j = ones[i];
            int k = zeroes[i];
                if(j>k){
                    gamma[i] = '1';
                    epsilon[i] = '0';
                }
                if(k>j) {
                    gamma[i] = '0';
                    epsilon[i] = '1';
                }
            }

        return Integer.valueOf(String.valueOf(gamma), 2) * Integer.valueOf(String.valueOf(epsilon), 2);
        }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
