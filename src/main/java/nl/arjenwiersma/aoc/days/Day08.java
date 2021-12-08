package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day08 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
        int counter = 0;
        for(String scrambled : input){
            boolean pipe = false;
            String[] scrambledSplit = scrambled.split("r | ");
            for(int i = 0; i < scrambledSplit.length; i++){
                String current = scrambledSplit[i];
                if(current.equals("|")){
                    pipe = true;
                }
                switch(current.length()) {
                        case 4: scrambledSplit[i] = "4 -> " + current;
                            if(pipe){counter++;}
                        break;
                        case 2: scrambledSplit[i] = "1 -> " + current;
                            if(pipe){counter++;}
                        break;
                        case 3: scrambledSplit[i] = "7 -> " + current;
                            if(pipe){counter++;}
                        break;
                        case 7: scrambledSplit[i] = "8 -> " + current;
                            if(pipe){counter++;}
                }
            }
        }
        return counter;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
