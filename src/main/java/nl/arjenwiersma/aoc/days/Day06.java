package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day06 implements Day<Long> {
    public Long countFishes(int days, List<String> input){
        int[] inputparsed = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::valueOf).toArray();
        List<Integer> fishes = new ArrayList<>();
        for(int i : inputparsed){
            fishes.add(i);
        }
        for(int day = 0; day < days; day++) {
            for (int i = fishes.size() - 1; i >= 0; i--) {
                int currentFish = fishes.get(i);
                if (currentFish == 0) {
                    fishes.add(8);
                    fishes.set(i,6);
                } else {
                    fishes.set(i, currentFish-1);
                }
            }
        }
        return (long) fishes.size();
    }
    @Override
    public Long part1(List<String> input) {
       return countFishes(80, input);
    }



    @Override
    public Long part2(List<String> input) {
        int[] inputparsed = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::valueOf).toArray();
        long[] counters = new long[9];
        for(int fish : inputparsed){
            counters[fish]++;
        }
        for(int day = 1; day <= 256; day++){
            long makeSixsAndEights = 0;
            for(int index = 0; index < 9; index++){
                switch(index){
                    case 0:
                        makeSixsAndEights = counters[index];
                        break;
                    default:
                        counters[index-1] = counters[index];
                }
            }
            counters[6] += makeSixsAndEights;
            counters[8] = makeSixsAndEights;
            }
        long sum = 0l;
        for(long i : counters){
            sum += i;
        }
        return sum; //countFishes(265, input);
    }
}
