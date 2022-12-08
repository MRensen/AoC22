package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day08 implements Day<Integer> {

    private class Tree implements Comparable<Tree>{
        public char value;
        public boolean visible = false;
        public int x,y;

        public Tree(char value, int x, int y){
            this.value = value;
            this.x=x;
            this.y=y;
        }

        @Override
        public int compareTo(Tree o) {
            if(this.value>o.value){
                return 1;
            } else if(this.value < o.value){
                return -1;
            } else {
                return 0;
            }
        }
    }

    public Tree[] getSubArray(int beginx, int beginy, int endx, int endy, Tree[][] forrest){
        Tree[] trees;
        int index = 0;
        if(beginx != endx){
            trees = new Tree[Math.abs(beginx-endx)];
        } else {
            trees = new Tree[Math.abs(beginy-endy)];
        }
        if(beginx<endx){
            while (beginx<endx){
                trees[index] = forrest[beginx][beginy];
                beginx++;
                index++;
            }
        }
        if(beginx>endx){
            while (beginx>endx){
                trees[index] = forrest[beginx][beginy];
                beginx--;
                index++;
            }
        }
        if (beginy<endy){
            while (beginy<endy){
                trees[index] = forrest[beginx][beginy];
                beginy++;
                index++;
            }
        }
        if (beginy>endy){
            while (beginy>endy){
                trees[index] = forrest[beginx][beginy];
                beginy--;
                index++;
            }
        }
        return trees;
    }

    @Override
    public Integer part1(List<String> input) {
        Tree[][] forrest = new Tree[input.size()][input.get(0).length()];
        int count = 0;
        for(int i = 0; i<input.size(); i++){
            char[] chars = input.get(i).toCharArray();
            for(int j = 0; j< chars.length; j++){
                forrest[i][j] = new Tree(chars[j],i,j);
            }
        }
        for (Tree[] trees : forrest) {
            for (Tree current : trees) {
                int v = current.value;
                Tree[] up = getSubArray(current.x, current.y, 0, current.y, forrest);
                Tree[] down = getSubArray(current.x, current.y, forrest.length, current.y, forrest);
                Tree[] left = getSubArray(current.x, current.y, current.x, 0, forrest);
                Tree[] right = getSubArray(current.x, current.y, current.x, trees.length, forrest);
                Tree upMax, downMax, leftMax, rightMax;
                Tree zero = new Tree('0', -1, -1);
                upMax = up.length > 0 ? Collections.max(Arrays.asList(up)) : null;
                downMax = down.length > 0 ? Collections.max(Arrays.asList(down)) : null;
                leftMax = left.length > 0 ? Collections.max(Arrays.asList(left)) : null;
                rightMax = right.length > 0 ? Collections.max(Arrays.asList(right)) : null;
                if(upMax==null||downMax==null||leftMax==null||rightMax==null){
                    current.visible = true;
                }else if(v < upMax.value || v < downMax.value || v < leftMax.value || v < rightMax.value) {
                    current.visible = true;
                }
//                if(v>upMax.value){
//                    current.visible = true;
//                }
//                if (v> downMax.value){
//                    current.visible = true;
//                }
//                if (v> leftMax.value){
//                    current.visible = true;
//                }
//                if (v> rightMax.value){
//                    current.visible = true;
//                }
                if (current.visible) {
                    System.out.println("x: " + current.x + ", y: " + current.y + ", v: " + current.value);
//                    System.out.printf("\t u: %c, d: %c, l: %c, r: %c \n", upMax.value, downMax.value, leftMax.value, rightMax.value);
                    count++;
                }
            }
        }


        return count;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
