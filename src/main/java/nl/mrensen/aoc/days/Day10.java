package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.List;

public class Day10 implements Day<Integer> {
    int tick = 0;
    int x = 1;
    int total = 0;
    int row = 0;
    int cursor = 0;
    char[][] buffer= new char[6][40];

    private void checkTick(){
        if(tick==20 ||
                tick==60 ||
                tick == 100 ||
                tick == 140 ||
                tick == 180 ||
                tick == 220){
//            System.out.println("Tick: " + tick + " X: " + x);
            total = total + (tick * x);
        }
    }

    private void printBuffer() {
        for(char[] row : buffer) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.print("\n");
        }
        buffer= new char[6][40];
        row = 0;
    }



    private char getChar(int x) {
              if(x == cursor || x-1 == cursor || x+1 == cursor) {
                    return '#';
              }              else {
                  return '.';
              }
    }


    private void drawTick(){
        if(cursor == 40) {
            row++;
            if(row<6) {
                cursor = 0;
                buffer[row][cursor] = getChar(x);
            } else {
                printBuffer();
            }
        } else {
            buffer[row][cursor] = getChar(x);
        }
        cursor++;

    }
    @Override
    public Integer part1(List<String> input) {
        for(String s: input){
            String command = s.substring(0,4);
            Integer toAdd;
            if(command.equals("addx")){
                tick++;
                checkTick();
                tick++;
                checkTick();
                toAdd = Integer.valueOf(s.substring(4).trim());
//                System.out.println(toAdd);
                x+=toAdd;
            } else {
                tick ++;
                checkTick();
            }
        }
        return total;
    }

    @Override
    public Integer part2(List<String> input) {
        for(String s: input){
            String command = s.substring(0,4);
            Integer toAdd;
            if(command.equals("addx")){
                tick++;
                drawTick();
                tick++;
                drawTick();
                toAdd = Integer.valueOf(s.substring(4).trim());
//                System.out.println(toAdd);
                x+=toAdd;
            } else {
                tick ++;
                drawTick();
            }
        }
        printBuffer();
        return total;
    }
    }

