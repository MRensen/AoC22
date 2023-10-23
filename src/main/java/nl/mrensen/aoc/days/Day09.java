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

   private void move(String move){
       String dir = move.substring(0,1);
       String steps = move.substring(1).trim();
       switch(dir){
           // voor elke richting die de H op kan gaan een aparte case, omdat het een combinatie van x/y ++/-- kan zijn.
           case "R" -> {
               // Voor elke stap die de H zet, volgt de T
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

   private boolean visitedAdd(Pos pos){
       Pos toAdd = new Pos(t.x, t.y);
       return visited.add(toAdd);
   };

    private void moveT() {
       // Als H naar rechts is verplaatst en T en H elkaar niet meer aanraken
        if(t.x<h.x-1){
            // verplaats T dan ook naar rechts, zodat T en H elakar weer aanraken.
            t.x++;
            // Als H dan ook nog hoger staat dan T, verplaats T dan schuin omhoog ipv enkel naar rechts.
            if(t.y<h.y){
                t.y++;
            }
            // Als H ook lager staat dan T, verplaats T dan schuin naar onder ipv enkel naar rechts.
            else if(t.y>h.y){
                t.y--;
            }
            if(visitedAdd(t)){
                count++;
            }
        }

        // Als H naar links is verplaatst en T en H elkaar niet meer aanraken
        else if(t.x>h.x+1){
            t.x--;
            if(t.y>h.y){
                t.y--;
            }
            else if(t.y<h.y){
                t.y++;
            }
            if(visitedAdd(t)){
                count++;
            }
        }

        // Als H omhoog is verplaats en T en H elkaar niet meer aanraken
        else if(t.y<h.y-1){
            t.y++;
            if(t.x<h.x){
                t.x++;
            }
            else if(t.x>h.x){
                t.x--;
            }
            if(visitedAdd(t)){
                count++;
            }
        }

        // Als H omlaag is verplaatst en T en H elkaar niet meer aanraken
        else if(t.y>h.y+1){
            t.y--;
            if(t.x>h.x){
                t.x++; //Note: Ik had hier ++ en hieronder --, waardoor mijn antwoord 6077 was ipv 5874
            }
            else if(t.x<h.x){
                t.x--;
            }
            if(visitedAdd(t)){
                count++;
            }
        }

    }



    @Override
    public Integer part1(List<String> input) {
        // voeg de start positie van T toe aan de set.
        visitedAdd(t);

        // loop door de input heen en voer elke regel uit.
        for(String s : input){
            inputRow++;
            move(s);
        }

        // return hoeveel unieke posities T heeft bezocht
        return visited.size();
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
