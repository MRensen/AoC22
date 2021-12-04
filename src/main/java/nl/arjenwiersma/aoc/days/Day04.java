package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day04 implements Day<Long> {
    Board winningboard = null;
    String winningCall = null;
    @Override
    public Long part1(List<String> input) {
        String[] calls = input.get(0).split(",");
        List<Board> boards = new ArrayList<>();
        for(int i = 5; i < input.size(); i+=5){
            //System.out.println(input.get(i));
           String[][] boardIn = new String[][]{input.get(i).split("\\s+"),
                                               input.get(i-1).split("\\s+"),
                                               input.get(i-2).split("\\s+"),
                                               input.get(i-3).split("\\s+"),
                                                input.get(i-4).split("\\s+")};
            boards.add(new Board(boardIn));
        }
        findWinningBoard(calls, boards);

        Long score = winningboard.getScore(winningCall);
        System.out.println(score);
        //System.out.println();

        return score;
    }
    public void findWinningBoard(String[] calls, List<Board> boards){
        for(String i : calls){
            for(Board board : boards){
                board.fillCall(i);
                if(board.hasBingo()){
                    winningboard = board;
                    winningCall = i;
                    return;
                }
            }
        }
    }

    @Override
    public Long part2(List<String> input) {
        return null;
    }
}

class Board{
    String[][] board = new String[5][5];

    public Board(String[][] board){
        this.board = board;
    }

    public void fillCall(String call){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].equals(call)){
                    board[i][j] = "filled";
                }
            }
        }
        System.out.println();
    }

    public boolean hasBingo(){
        for(String[] row : board){
            if(row[0].equals("filled") &&
                    row[1].equals("filled") &&
                    row[2].equals("filled") &&
                    row[3].equals("filled") &&
                    row[4].equals("filled")){
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public long getScore(String i){
        int sum = 0;
        for(String[] row : board){
            for(String str : row){
                if(!str.equals("filled")) {
                    sum += Integer.parseInt(str);
                }
            }
        }
        Long result = Integer.toUnsignedLong(sum * Integer.parseInt(i));
        System.out.println(result);
        return result;
    }
}
