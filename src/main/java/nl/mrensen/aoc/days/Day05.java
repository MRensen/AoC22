package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.*;

public class Day05 implements Day<String> {
    Stack<String> s1 = new Stack<>(),s2 = new Stack<>(),s3 = new Stack<>(),s4 = new Stack<>(),s5 = new Stack<>(),
            s6 = new Stack<>(),s7 = new Stack<>(),s8 = new Stack<>(),s9 = new Stack<>(), s10 = new Stack<>();
    record Command(int amount, int start, int dest){};
    List<Stack<String>> stacks;

    public Day05(){

//        TestData

//        String[][] aCollect = {
//                {"N","Z"},
//                {"D","C","M"},
//                {"P"}
//        };
//        stacks = List.of(s1,s2,s3);

//      RealData

        String[][] aCollect = {
                {"F","R","W"},
                {"P","W","V","D","C","M","H","T"},
                {"L","N","Z","M","P"},
                {"R","H","C","J"},
                {"B","T","Q","H","G","P","C"},
                {"Z","F","L","W","C","G"},
                {"C","G","J","Z","Q","L","V","W"},
                {"C","V","T","W","F","R","N","P"},
                {"V","S","R","G","H","W","J"}
        };
        stacks = List.of(s1,s2,s3,s4,s5,s6,s7,s8,s9);


        for(int i = 0; i < aCollect.length; i++){
            stacks.get(i).addAll(Arrays.stream(aCollect[i]).toList());
            Collections.reverse(stacks.get(i));
        }

    }

    private List<Command> parseInput (List<String> input){

        List<Command> c = new ArrayList<>();
        for(String s : input){
            String[] split = s.split(" ");
            c.add(new Command(Integer.parseInt(split[1]),
                    Integer.parseInt(split[3])-1,
                    Integer.parseInt(split[5])-1
            ));
        }
        return c;
    }

    private String getTop(){
        StringBuilder sb = new StringBuilder();
        for(Stack<String> s : stacks){
            sb.append(s.pop());
        }
        return sb.toString();
    }

    @Override
    public String part1(List<String> input) {
//        input = List.of("move 1 from 2 to 1" ,
//                "move 3 from 1 to 3" ,
//                "move 2 from 2 to 1" ,
//                "move 1 from 1 to 2");
        List<Command> commands = parseInput(input);

        for(Command c : commands){
            for(int i = c.amount; i > 0; i--){
                String pop = stacks.get(c.start).pop();
                stacks.get(c.dest).push(pop);
            }
        }

        return getTop();
    }

    @Override
    public String part2(List<String> input) {
//        input = List.of("move 1 from 2 to 1" ,
//                "move 3 from 1 to 3" ,
//                "move 2 from 2 to 1" ,
//                "move 1 from 1 to 2");
        List<Command> commands = parseInput(input);

        for(Command c : commands){
            String[] s = new String[c.amount];
            for(int i = c.amount; i > 0; i--){
                String pop = stacks.get(c.start).pop();
                s[i-1]=pop;
            }
            stacks.get(c.dest).addAll(Arrays.stream(s).toList());
        }

        return getTop();
    }
}
