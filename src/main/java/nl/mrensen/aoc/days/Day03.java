package nl.mrensen.aoc.days;

import com.google.common.base.Joiner;
import nl.mrensen.aoc.common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day03 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
//        input = Arrays.asList("vJrwpWtwJgWrhcsFMMfFFhFp" ,
//                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL" ,
//                "PmmdzqPrVvPwwTWBwg" ,
//                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn" ,
//                "ttgJtRGJQctTZtZT" ,
//                "CrZsJsPPZsGzwwsLwLmpwMDw");
        List<Set<Character>> s3 = new ArrayList<>();
        for (String s : input) {
//            Character[] s1 = s.substring(0, s.length()/2).chars().sorted().mapToObj(c -> (char)c).toArray(Character[]::new);
//            Character[] s2 = s.substring(s.length()/2).chars().sorted().mapToObj(c -> (char)c).toArray(Character[]::new);
            List<Character> s1 = s.substring(0, s.length() / 2).chars().mapToObj(c -> (char) c).toList();
            List<Character> s2 = s.substring(s.length() / 2).chars().mapToObj(c -> (char) c).toList();
            Set<Character> s4 = new HashSet<>();
            if (s1.size() != s2.size()) {
                throw new RuntimeException("Items are not equal in length");
            }


            for (Character c : s1) {
                if (s2.contains(c)) {
                    s4.add(c);
//                        output += Character.isUpperCase(c)?c-38:c-96;
                }
            }
            s3.add(s4);
//            System.out.println(Joiner.on("").join(s1));
//            System.out.println(Joiner.on("").join(s2));

        }
        return s3.stream()
                .mapToInt((Set<Character> s) -> s.stream()
                        .mapToInt((Character c) -> Character.isUpperCase(c) ? c - 38 : c - 96)
                        .sum())
                .sum();//  (Character c)->Character.isUpperCase(c)?c-38:c-96).sum();
    }

    @Override
    public Integer part2(List<String> input) {
        List<List<String>> groups = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (String s : input) {
            if (temp.size() < 3) {
                temp.add(s);
            } else {
                groups.add(temp);
            }
        }

//        groups.stream()
//                .mapToInt((List<String> l)->l.stream().mapToInt(()->{})
//                        .sum())
//                .sum();


        System.out.println(groups);
        return groups.size();
    }
}
