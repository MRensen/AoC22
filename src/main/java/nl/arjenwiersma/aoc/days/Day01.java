package nl.arjenwiersma.aoc.days;

import java.util.List;

import nl.arjenwiersma.aoc.common.Day;

public class Day01 implements Day<Integer> {

    @Override
    public Integer part1(List<String> input) {
        int previous = 0;
        int bigger = 0;
        int smaller = 0;
        for(String i : input){
            int current = Integer.parseInt(i);
            if(previous > 0){
                if(current > previous){
                    bigger++;
                } else {
                    smaller++;
                }
            }
            previous = current;
        }
        return bigger;
    }

    @Override
    public Integer part2(List<String> input) {
        int bigger = 0;
        int clock = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int previousSum = 0;
        for(String i : input){
            int current = Integer.parseInt(i);
            int currentSum = 0;
            if(a+b+c == 0){
                a = current;
            } else if(b+c == 0){
                b = current;
            } else if(c == 0){
                c = current;
                currentSum = a+b+c;
                previousSum = currentSum;
            } else {
                switch(clock){
                    case 0:
                        clock = 1;
                        a = current;
                        break;
                    case 1:
                        clock = 2;
                        b = current;
                        break;
                    case 2:
                        clock = 0;
                        c = current;
                        break;
                }
                currentSum = a+b+c;
                if(currentSum > previousSum){
                    bigger++;
                }
                previousSum = currentSum;
            }
        }
        return bigger;
    }
    
}
