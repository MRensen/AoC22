package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day04 implements Day<Long> {
    @Override
    public Long part1(List<String> input) {
        String[] calls = input.get(0).split(",");
        List<Board> boards = new ArrayList<>();
        for(int i = 6; i < input.size(); i+=5){
           String[][] boardIn = new String[][]{input.get(i).trim().split("\\s+"),
                                               input.get(i-1).trim().split("\\s+"),
                                               input.get(i-2).trim().split("\\s+"),
                                               input.get(i-3).trim().split("\\s+"),
                                                input.get(i-4).trim().split("\\s+")};
            boards.add(new Board(boardIn));
            i++;
        }
        Board winningBoard = null;
        String winningCall = null;
        while(winningBoard == null) {
            for (String call : calls) {
                Board temp = findWinningBoard(call, boards);
                if(temp != null){
                    winningBoard = temp;
                    winningCall = call;
                    break;
                }
            }
        }

        try {
            Long score = winningBoard.getScore(winningCall);
            return score;
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Board findWinningBoard(String call, List<Board> boards){
            for(Board board : boards){
                board.fillCall(call);
                if(board.hasBingo()){
                    return board;
                }
        }
            return null;
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
    }
    public boolean checkCollumns(String[][] board){
        for(int i = 0; i < board.length; i++){
            if( board[0][i].equals("filled")&&
                    board[1][i].equals("filled")&&
                    board[2][i].equals("filled")&&
                    board[3][i].equals("filled")&&
                    board[4][i].equals("filled")){
                return true;
            }
        }
        return false;
    }

    public boolean hasBingo(){
        for(String[] row : board){
            if((row[0].equals("filled") &&
                    row[1].equals("filled") &&
                    row[2].equals("filled") &&
                    row[3].equals("filled") &&
                    row[4].equals("filled")) ||
            checkCollumns(board)){
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
                    try {
                        sum += Integer.parseInt(str);
                    } catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        Long result = Integer.toUnsignedLong(sum * Integer.parseInt(i));
        return result;
    }
}
