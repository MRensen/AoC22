package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day08 implements Day<Integer> {

    // maak een grid ("forrest")
    Tree[][] forrest;

    // Maak arrays om te bepalen wat de laatste zichtbare boom in elke rij of kolom is
    // (left to right, right to left, top to bottom, bottom to top)
    Tree[] ltr;
    Tree[] rtl;
    Tree[] ttb;
    Tree[] btt;


    private void fillForrestWithTrees(List<String> input) {
        // vul het "forrest" met bomen (x,y,val)
        for (int i = 0; i < input.size(); i++) {
            char[] chars = input.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                forrest[i][j] = new Tree(chars[j], i, j);
            }
        }
    }

    private void initializeVisibilityArrays(List<String> input) {
        ttb = new Tree[input.size()]; //i++
        btt = new Tree[input.size()]; //i--
        rtl = new Tree[input.size()]; //j++
        ltr = new Tree[input.size()]; //j--
    }

    // check welke bomen van buitenaf zichtbaar zijn
    private void calculateVisibilityPerTreePerSide() {
        // De eerste for loop doorloopt alle rijen en kolommen oplopend
        for (int i = 0; i < forrest.length ; i++) {
            for(int j = 0; j < forrest[i].length ; j++) {
                Tree current = forrest[i][j];

                //check ttb
                // Als de "current" Tree hoger is dan de huidige hoogste Tree voor deze kolom boven zich
                // (opgelagen in de ttb array), dan is deze boom zichtbaar vanaf boven
                if (ttb[j] == null || ttb[j].value < current.value) {
                    current.visibleTop = true;
                    ttb[j] = current;
                }
                //check rtl
                // Als de "current" Tree hoger is dan de huidige hoofste Tree voor deze rij links van zich
                // (opgeslagen inde rtl array), dan is deze boom zichtbaar vanaf links
                if (rtl[i] == null || rtl[i].value < current.value){
                    current.visibleLeft = true;
                    rtl[i] = current;
                }

            }
        }

        // De tweede for-loop doorloopt alle kolommen en rijen aflopend
        for (int i = forrest.length -1; i >= 0 ; i--) {
            for (int j = forrest[i].length -1; j >= 0; j--) {
                Tree current = forrest[i][j];

                //check btt
                if (btt[j] == null || btt[j].value < current.value) {
                    current.visibleBottom = true;
                    btt[j] = current;
                }

                //check ltr
                if (ltr[i] == null || ltr[i].value < current.value){
                    current.visibleRight = true;
                    ltr[i] = current;
                }
            }
        }
    }

    private void calculateViewingDistancePerTreePerSide() {
        for (int i = 0; i < forrest.length ; i++) {
            for (int j = 0; j < forrest[i].length; j++) {

                Tree current = forrest[i][j];

                if(i==3 && j==2){
                    System.out.println();
                }

                // check left
                // reken vanaf de huidige row index (j) terug naar row index 0 en check hoeveel zichtbare bomen daar staan.
                if(j>0) {
                    for (int k = j - 1; k >= 0; k--) {
                        Tree check = forrest[i][k];

                        //Als je een boom tegenkomt die lager is dan de huidige boom, dan kun je die zien en kun je verder kijken wat je nog meer ziet
                        if (check.value < current.value) {
                            current.viewingDistanceLeft++;
                        }

                        //Als je een boom tegenkomt die even hoog of hoger is dan de huidige boom, dan kun je die zien, maar kun je niet meer verder kijken.
                        if (check.value >= current.value) {
                            current.viewingDistanceLeft++;
                            break;
                        }
                    }
                }

                // check right
                // reken vanaf de huidige row index (j) verder naar rechts en kijk hoeveel zichtbare bomen daar staan,
                if(j < forrest[i].length) {
                    for (int k = j+1; k < forrest[i].length; k++) {
                        Tree check = forrest[i][k];
                        if (check.value < current.value) {
                            current.viewingDistanceRight++;
                        }
                        if (check.value >= current.value) {
                            current.viewingDistanceRight++;
                            break;
                        }
                    }
                }

                // check top
                // Check hoeveel zichtbare bomen er boven deze boom staan
                if(i>0){
                    for(int k = i-1; k>=0; k--){
                        Tree check = forrest[k][j];
                        if (check.value < current.value) {
                            current.viewingDistanceTop++;
                        }
                        if (check.value >= current.value) {
                            current.viewingDistanceTop++;
                            break;
                        }
                    }
                }

                // check bottom
                // check hoeveel zichtbare bomen er onder deze boom staan
                if(j < forrest.length){
                    for (int k = i+1; k < forrest.length; k++) {
                        Tree check = forrest[k][j];
                        if (check.value < current.value) {
                            current.viewingDistanceBottom++;
                        }
                        if (check.value >= current.value) {
                            current.viewingDistanceBottom++;
                            break;
                        }
                    }
                }
            }
        }
    }

    private int calculateScenicScore(Tree tree) {
        // Vermeniguvldig alle "viewingdistances" van alle richtingen om de "scenicScore" te krijgen.
        return tree.viewingDistanceLeft * tree.viewingDistanceRight * tree.viewingDistanceTop * tree.viewingDistanceBottom;
    }


    @Override
    public Integer part1(List<String> input) {

        // vul het "forrest" grid met een lege grid van 99 bij 99
        forrest = new Tree[input.size()][input.get(0).length()];

        fillForrestWithTrees(input);

        initializeVisibilityArrays(input);

        calculateVisibilityPerTreePerSide();

        int count = 0;
        // Loop alle bomen nog eens na en kijk of ze zichtbaar zijn vanaf een kant.
        for (Tree[] trees : forrest){
            for(Tree tree : trees){
                if (tree.visibleBottom || tree.visibleRight || tree.visibleLeft || tree.visibleTop){
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public Integer part2(List<String> input) {
        forrest = new Tree[input.size()][input.get(0).length()];

        fillForrestWithTrees(input);

        initializeVisibilityArrays(input);

        calculateViewingDistancePerTreePerSide();

        int hightesScenicScore = 0;
        // Loop alle bomen nog eens na en bereken hun "scenic score".
        for (Tree[] trees : forrest){
            for(Tree tree : trees){
                int currentScenicScore = calculateScenicScore(tree);
                if(currentScenicScore > hightesScenicScore){
                    hightesScenicScore = currentScenicScore;
                }
            }
        }

        return hightesScenicScore;
    }



    // Maak een Tree klasse, om voor elke int uit de input wat bijbehorende informatie op te kunnen slaan
    // (met name of deze zichtbaar is vanaf een bepaalde kant)
    private class Tree{
        public char value;
        public boolean visibleLeft = false;
        public boolean visibleRight = false;
        public boolean visibleTop = false;
        public boolean visibleBottom = false;
        public int viewingDistanceLeft;
        public int viewingDistanceRight;
        public int viewingDistanceTop;
        public int viewingDistanceBottom;

        // deze zijn handig voor debuggen om te bepalen welke boom het is
        public int x,y;

        public Tree(char value, int x, int y){
            this.value = value;
            this.x=x;
            this.y=y;
        }

    }

}
