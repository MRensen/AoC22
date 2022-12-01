package nl.mrensen.aoc.days;

import java.util.ArrayList;
import java.util.List;

import nl.mrensen.aoc.common.Day;

public class Day01 implements Day<Integer> {

    @Override
    public Integer part1(List<String> input) {
        List<Elf> elves = new ArrayList<>();
        List<String> toElf = new ArrayList<>();
        Elf winner = new Elf();
        for(String c : input){
            if(!c.isEmpty()){
                toElf.add(c);
            } else {
                elves.add(new Elf(toElf));
                toElf = new ArrayList<>();
            }
        }
        for(Elf elf : elves){
            if(elf.getTotalCalories() > winner.getTotalCalories()){
                winner = elf;
            }
        }
        return winner.getTotalCalories();

    }
    private class Elf{
        final List<String> calories;
        public Elf(List<String> calories){
            this.calories = calories;
        }
        public Elf(){
            this.calories = List.of("0");
        }

        public int getTotalCalories(){
            int total = 0;
            for(String c : calories){
                total += Integer.parseInt(c);
            }
            return total;
        }

        @Override
        public String toString() {
            return Integer.toString(getTotalCalories());
        }
    }

    @Override
    public Integer part2(List<String> input) {
//        input = Arrays.asList("1000", "2000", "3000", "" , "4000","","5000","6000","","7000","8000","9000","","10000");
        List<Elf> elves = new ArrayList<>();
        List<String> toElf = new ArrayList<>();
        Elf winner = new Elf();
        Elf second = new Elf();
        Elf third = new Elf();
        for(String c : input){
            if(!c.isEmpty()){
                toElf.add(c);
            } else {
                elves.add(new Elf(toElf));
                toElf = new ArrayList<>();
            }
        }
        if(!elves.isEmpty()) {
            elves.add(new Elf(toElf));
        }
        for(Elf elf : elves){
            if(elf.getTotalCalories() > winner.getTotalCalories()){
                third = second;
                second = winner;
                winner = elf;
            } else if(elf.getTotalCalories() > second.getTotalCalories()){
                third = second;
                second = elf;
            }else if(elf.getTotalCalories() > third.getTotalCalories()){
                third = elf;
            }

        }
        System.out.println(winner + " + " + second +" + "+ third);
        return winner.getTotalCalories() + second.getTotalCalories() + third.getTotalCalories();

    }
    
}
