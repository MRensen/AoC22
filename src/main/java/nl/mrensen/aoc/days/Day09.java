package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.*;

public class Day09  implements Day<Integer> {

    int inputRow = 0;

    // Een handige helper klasse om de positie van (x,y) van de H en T op te schrijven.
    // Equals en Hashcode zijn heel belangrijk, anders krijg je dubbele waardes in de Set.
    private class Pos{
        public int x, y;
        public Pos(int x, int y){this.x=x; this.y=y;}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // x=r/l  y=u/d
   Pos h = new Pos(0,0);
   Pos t = new Pos(0, 0);
   int count = 1;
   Set<Pos> visited = new HashSet<>();

   private void move(String move, Pos head, Pos tail, boolean set){
       String dir = move.substring(0,1);
       String steps = move.substring(1).trim();
       switch(dir){
           // voor elke richting die de H op kan gaan een aparte case, omdat het een combinatie van x/y ++/-- kan zijn.
           case "R" -> {
               // Voor elke stap die de H zet, volgt de T
               for(int i = 0; i < Integer.parseInt(steps); i++){
                   head.x++;
                   moveT(head, tail, set);
               }
           }
           case "L" -> {
               for(int i = 0; i < Integer.parseInt(steps); i++){
                   head.x--;
                   moveT(head, tail, set);
               }
           }
           case "U" -> {
               for(int i = 0; i < Integer.parseInt(steps); i++){
                   head.y++;
                   moveT(head,tail, set);
               }
           }
           case "D" -> {
               for(int i = 0; i < Integer.parseInt(steps); i++){
                   head.y--;
                   moveT(head, tail, set);
               }
           }
       }
   }

   private boolean visitedAdd(Pos pos, Boolean set){
       Pos toAdd = new Pos(pos.x, pos.y);
       
       // Voeg alleen toAdd toe aan visited als set true is.
       return set && visited.add(toAdd);
   };

    private void moveT(Pos head, Pos tail, boolean set) {
       // Als H naar rechts is verplaatst en T en H elkaar niet meer aanraken
        if(tail.x<head.x-1){
            // verplaats T dan ook naar rechts, zodat T en H elkaar weer aanraken.
            tail.x++;
            // Als H dan ook nog hoger staat dan T, verplaats T dan schuin omhoog ipv enkel naar rechts.
            if(tail.y<head.y){
                tail.y++;
            }
            // Als H ook lager staat dan T, verplaats T dan schuin naar onder ipv enkel naar rechts.
            else if(tail.y>head.y){
                tail.y--;
            }
            if(visitedAdd(tail, set)){
                count++;
            }
        }

        // Als H naar links is verplaatst en T en H elkaar niet meer aanraken
        else if(tail.x>head.x+1){
            tail.x--;
            if(tail.y>head.y){
                tail.y--;
            }
            else if(tail.y<head.y){
                tail.y++;
            }
            if(visitedAdd(tail, set)){
                count++;
            }
        }

        // Als H omhoog is verplaats en T en H elkaar niet meer aanraken
        else if(tail.y<head.y-1){
            tail.y++;
            if(tail.x<head.x){
                tail.x++;
            }
            else if(tail.x>head.x){
                tail.x--;
            }
            if(visitedAdd(tail, set)){
                count++;
            }
        }

        // Als H omlaag is verplaatst en T en H elkaar niet meer aanraken
        else if(tail.y>head.y+1){
            tail.y--;
            if(tail.x>head.x){
                tail.x--; //Note: Ik had hier ++ en hieronder --, waardoor mijn antwoord 6077 was ipv 5874
            }
            else if(tail.x<head.x){
                tail.x++;
            }
            if(visitedAdd(tail, set)){
                count++;
            }
        }

    }



    @Override
    public Integer part1(List<String> input) {
        // voeg de start positie van T toe aan de set.
        visitedAdd(t ,true);

        // loop door de input heen en voer elke regel uit.
        for(String s : input){
            inputRow++; // deze is handig voor debuggen
            move(s, h, t, true);
        }

        // return hoeveel unieke posities T heeft bezocht
        return visited.size();
    }

    @Override
    public Integer part2(List<String> input) {
        // voeg de start positie van T toe aan de set.
        visitedAdd(t, true);
        
        Pos[] tails = new Pos[8];
        for(int i = 0; i<tails.length; i++){
            tails[i] = new Pos(0, 0);
        }

        // loop door de input heen en voer elke regel uit voor alle knopen
        for(String s : input){
            String steps = s.substring(1).trim();
            inputRow++;
            move(s, h, tails[0], false);
            for(int i = 0; i < Integer.parseInt(steps); i++) {
                moveT(tails[0], tails[1], false);
                moveT(tails[1], tails[2], false);
                moveT(tails[2], tails[3], false);
                moveT(tails[3], tails[4], false);
                moveT(tails[4], tails[5], false);
                moveT(tails[5], tails[6], false);
                moveT(tails[6], tails[7], false);
                moveT(tails[7], t, true);
            }
        }

        // return hoeveel unieke posities T heeft bezocht
        return visited.size();
    }
}
