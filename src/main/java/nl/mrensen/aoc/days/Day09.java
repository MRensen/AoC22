package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

class Pos{
    public int x, y;
    public Pos(int x, int y){}
}

public class Day09  implements Day<Integer> {

    // x=r/l  y=u/d
   Pos h = new Pos(0,0);
   Pos t = new Pos(0, 0);
   int count = 1;
   Set<Pos> visited = new HashSet<>();

   private void move(String move){
       String dir = move.substring(0,1);
       String steps = move.substring(1).trim();
       switch(dir){
           case "R" -> {
               for(int i = 0; i < Integer.parseInt(steps); i++){
                   h.x++;
                   moveT();
               }
           }
           case "L" -> {
               for(int i = 0; i < Integer.parseInt(steps); i++){
                   h.x--;
                   moveT();
               }
           }
           case "U" -> {
               for(int i = 0; i < Integer.parseInt(steps); i++){
                   h.y++;
                   moveT();
               }
           }
           case "D" -> {
               for(int i = 0; i < Integer.parseInt(steps); i++){
                   h.y--;
                   moveT();
               }
           }
       }
   }

   private boolean visitedAdd(Pos t){
       return visited.add(new Pos(t.x, t.y));
   };

    private void moveT() {
//       if(t.x<h.x-1 && t.y<h.y-1){
//           t.x++;
//           t.y++;
//           if(visitedAdd(t)){
//               count++;
//           };
//       }
//       if(t.x>h.x+1 && t.y>h.y+1){
//           t.x--;
//           t.y--;
//           if(visitedAdd(t)){
//               count++;
//           };
//
//       }
        if(t.x<h.x-1){
            t.x++;
            if(t.y<h.y){
                t.y++;
            }
            if(t.y>h.y){
                t.y--;
            }
            if(visitedAdd(t)){
                count++;
            }
        }
        if(t.x>h.x+1){
            t.x--;
            if(t.y>h.y){
                t.y--;
            }
            if(t.y<h.y){
                t.y++;
            }
            if(visitedAdd(t)){
                count++;
            }
        }
        if(t.y<h.y-1){
            t.y++;
            if(t.x<h.x){
                t.x++;
            }
            if(t.x>h.x){
                t.x--;
            }
            if(visitedAdd(t)){
                count++;
            }
        }
        if(t.y>h.y+1){
            t.y--;
            if(t.x>h.x){
                t.x++;
            }
            if(t.x<h.x){
                t.x--;
            }
            if(visitedAdd(t)){
                count++;
            }
        }
//        System.out.printf("H(x:%d, y:%d), T(x:%d, y%d)\n", h.x, h.y, t.x, t.y);
    }



    @Override
    public Integer part1(List<String> input) {
        for(String s : input){
            move(s);
        }


        return count;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
