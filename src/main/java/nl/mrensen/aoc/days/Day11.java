package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 implements Day<Integer> {

    List<Monkey> monkeys = new ArrayList<>();

    private class Monkey{
        int inspections = 0;
        List<Integer> items;
        int id, divisible, correct, incorrect, operation;
        String operator;
        public Monkey(int id, List<Integer> items, String operation, int divisible, int correct, int incorrect){
            this.id = id;
            this.items = items;
            this.divisible = divisible;
            this.correct = correct;
            this.incorrect = incorrect;
            String[] split = operation.split(" ");
            operator = split[0];
            try {
                this.operation = Integer.parseInt(split[1]);
            } catch (NumberFormatException e){
                if(!split[1].equals("old")){
                    throw new RuntimeException("OLD expected");
                }
                operator = "^";
            }
        }
        public void addItem(int item){
            items.add(item);
        }

        public void inspect(){

            if(items.isEmpty()){
//                System.out.println("no items");
            } else {
                for (int item : items) {
                    inspections++;
                    item = itemOperation(item);
                    item = item / 3;
                    if (item % divisible == 0) {
                        monkeys.get(correct).addItem(item);
                    } else {
                        monkeys.get(incorrect).addItem(item);
                    }
                }
                items = new ArrayList<>();
            }
        }

        private int itemOperation(int item){
            switch (operator){
                case "^" -> {return item * item;}
                case "+" -> {
                    return item + operation;
                }
                case "-" -> {
                    return item - operation;
                }
                case "*" -> {return item * operation;}

            }
            throw new RuntimeException("operator error");
        }
    }

    private void parseInput(List<String> input){
        int monkeyNumber = 0;
        for(int i = 0; i < input.size(); i+=7){
            List<String> rawMonkey = input.subList(i,i+6);
            var itemcheck = rawMonkey.get(1).substring(18).split(", ");
            List<Integer> items = Stream.of(rawMonkey.get(1).substring(18).split(", "))
                    .map(Integer::valueOf).collect(Collectors.toList());
            String operation = rawMonkey.get(2).substring(23);
            int divisible = Integer.parseInt(rawMonkey.get(3).substring(21));
            int correct = Integer.parseInt(rawMonkey.get(4).substring(29));
            int incorrect = Integer.parseInt(rawMonkey.get(5).substring(30));
            Monkey monkey = new Monkey(monkeyNumber, items, operation, divisible, correct, incorrect);
            monkeys.add(monkeyNumber, monkey);


//            System.out.println("test");
            monkeyNumber++;
        }
    }

    private void printAllItems(){
        for(int i = 0; i < monkeys.size(); i++){
            System.out.println("Monkey " + i + ": " + monkeys.get(i).items +
                    ", inspections: " + monkeys.get(i).inspections);
        }
    }

    @Override
    public Integer part1(List<String> input) {
        parseInput(input);
        for(int i = 0; i < 20; i++) {
            for (Monkey m : monkeys) {
                m.inspect();
            }
        }
        printAllItems();
        return null;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
