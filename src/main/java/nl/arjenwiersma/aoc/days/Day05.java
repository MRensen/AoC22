package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;

import java.util.Arrays;
import java.util.List;

public class Day05 implements Day<Integer> {
    public int[][] getHorizontal(int x, int y, int constant){
        //x>y
        int[][] result = new int[(x-y)+1][2];
        for(int i = y; i <= x; i++){
            //int[x,const] for horizontal
            result[i-y] = new int[]{constant, i};
        }
        return result;
    }
    public int[][] getVertical(int x, int y, int constant){
        //x>y
        int[][] result = new int[(x-y)+1][2];
        for(int i = y; i <= x; i++){
            //int[const,x] for vertical
            result[i-y] = new int[]{i, constant};
        }
        return result;
    }
    public int[][] drawLine(int[] x, int[] y){
        // Only horizontal and vertical lines, not diagonals
        int[][] line = null;
        if(x[1] == y[1]){
            //horizontal
            if(x[0] > y[0]){
                return getHorizontal(x[0], y[0], x[1]);
            } else {
                return getHorizontal(y[0], x[0], x[1]);
            }
        }else if(x[0] == y[0]){
            //vertical
            if(x[1] > y[1]){
                return getVertical(x[1], y[1], x[0]);
            } else {
                return getVertical(y[1], x[1], x[0]);
            }
        }
        return null;
    }

    public int countDangers(int[][] board){
        int result = 0;
        for(int[] row : board){
            for(int coord : row){
                if(coord > 1){
                    result++;
                }
            }
        }
        return result;
    }
    @Override
    public Integer part1(List<String> input) {
        int gamboardSize = 10;
        if(input.size() > 10){
            gamboardSize = 1000;
        }
        //String[10][10] = samplesize
        //String[1000][1000] = inputsize
        int[][] gameboard = new int[gamboardSize][gamboardSize];
        for(String str : input){
            String[] commands = str.split(" -> ");
            int[] x = Arrays.stream(commands[0].split(",")).mapToInt(Integer::valueOf).toArray();
            int[] y = Arrays.stream(commands[1].split(",")).mapToInt(Integer::valueOf).toArray();
            int[][] line = drawLine(x,y);
            if(line != null){
                for(int[] coord : line){
                    gameboard[coord[0]][coord[1]]++;
                }
            }
        }
        return countDangers(gameboard);
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
