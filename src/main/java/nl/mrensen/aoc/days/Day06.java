package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
        String signal = input.get(0);
//        signal = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        char[] signalarray = signal.toCharArray();

        for(int i = 0 ; i < signalarray.length-4 ; i++){
            Set<Character> window = new HashSet<>();
            boolean a = window.add(signalarray[i]), b = window.add(signalarray[i+1]), c = window.add(signalarray[i+2]), d = window.add(signalarray[i+3]);
            if(a&&b&&c&&d){
                return i + 4;
            }
        }
        return -1;
    }

    @Override
    public Integer part2(List<String> input) {
        String signal = input.get(0);
//        signal = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        char[] signalarray = signal.toCharArray();

        for(int i = 0 ; i < signalarray.length-14 ; i++){
            Set<Character> window = new HashSet<>();
            List<Boolean> bools = new ArrayList<>();
            for(int j = 0; j < 14; j++){
                bools.add(window.add(signalarray[i+j]));
            }

           if(bools.stream().reduce((a,b)->a&&b).get()){
               return i+14;
           }
        }
        return -1;
    }
}
