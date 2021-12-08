package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;



public class Day07 implements Day<Integer> {
    public int calcx(int x, List<Integer> input){
        List<Integer> diffs = new ArrayList<>();
       for(int i : input){
           diffs.add(Math.abs(i-x));
       }
       int sum = 0;
       for(int i: diffs){
           sum += i;
       }
        return sum;
    }
    public int calcy(int y, List<Integer> input){
        int fiftyfifty = (100/2*(2+(100-1)));
        List<Integer> diffs = new ArrayList<>();
        for(int i : input){
            diffs.add(Math.abs(i-y));
        }
        int sum = 0;
        for(int i: diffs){
            //S = n/2[2a + (n − 1) × d]

            int calc = (i/2*(2+(i)));
            System.out.println(i + " -> " +calc);
            if(i == 1){
                sum+= 1;
            } else {
                sum += calc;
            }
        }
        return sum;
    }
    @Override
    public Integer part1(List<String> input) {
        List<Integer> parsedInput = new ArrayList<>();
        for(String str : input.get(0).split(",")){
            parsedInput.add(Integer.valueOf(str));
        }
        int highest = -1;
        for(int i = 0; i<2000; i++){
            int current = calcx(i, parsedInput);
            if(current < highest || highest == -1){
                highest = current;
            }

        }

        return highest;
    }

    @Override
    public Integer part2(List<String> input) {
        List<Integer> parsedInput = new ArrayList<>();
        for(String str : input.get(0).split(",")){
            parsedInput.add(Integer.valueOf(str));
        }
        int highest = -1;
        for(int i = 0; i< parsedInput.size(); i++){
            int current = calcy(i, parsedInput);
            if(current < highest || highest == -1){
                highest = current;
            }

        }

        return highest;
    }
}
