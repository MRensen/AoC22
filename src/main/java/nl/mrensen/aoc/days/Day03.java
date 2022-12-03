package nl.mrensen.aoc.days;

import com.google.common.base.Joiner;
import nl.mrensen.aoc.common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day03 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
        List<Set<Character>> s3 = new ArrayList<>();
        for (String s : input) {
            List<Character> s1 = s.substring(0, s.length() / 2).chars().mapToObj(c -> (char) c).toList();
            List<Character> s2 = s.substring(s.length() / 2).chars().mapToObj(c -> (char) c).toList();
            Set<Character> s4 = new HashSet<>();
            if (s1.size() != s2.size()) {
                throw new RuntimeException("Items are not equal in length");
            }
            for (Character c : s1) {
                if (s2.contains(c)) {
                    s4.add(c);
                }
            }
            s3.add(s4);
        }
        return s3.stream()
                .mapToInt((Set<Character> s) -> s.stream()
                        .mapToInt(this::itemValue)
                        .sum())
                .sum();
    }

    @Override
    public Integer part2(List<String> input) {
//        input = Arrays.asList("vJrwpWtwJgWrhcsFMMfFFhFp" ,
//                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL" ,
//                "PmmdzqPrVvPwwTWBwg" ,
//                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn" ,
//                "ttgJtRGJQctTZtZT" ,
//                "CrZsJsPPZsGzwwsLwLmpwMDw");
        List<List<String>> groups = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (String s : input) {
            if (temp.size() < 3) {
                temp.add(s);
            } else {
                groups.add(temp);
                temp = new ArrayList<>();
                temp.add(s);
            }
        }
        groups.add(temp);
        List<Character> l1, l2, l3, l4;
        l4 = new ArrayList<>();
        for(List<String> l : groups){
            l1 = l.get(0).chars().mapToObj(c -> (char) c).toList();
            l2 = l.get(1).chars().mapToObj(c -> (char) c).toList();
            l3 = l.get(2).chars().mapToObj(c -> (char) c).toList();
            for(Character c : l1){
                if(l2.contains(c)&&l3.contains(c)){
                    l4.add(c);
                    break;
                }
            }
        }
        return l4.stream().mapToInt(this::itemValue).sum();
    }

    public Integer itemValue(Character c){
        return Character.isUpperCase(c) ? c - 38 : c - 96;
    }
}
