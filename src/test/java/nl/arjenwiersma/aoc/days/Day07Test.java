package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;
import nl.arjenwiersma.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day07Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(07);

    @Test
    public void part1() {
        Day<Integer> day = new Day07();
        assertEquals(Integer.valueOf(336131), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day07();
        assertEquals(Integer.valueOf(0), day.part2(input.getLines()));
    }

    @Test
    public void sample(){
        List<String> sample = Arrays.stream(new String[]{"16,1,2,0,4,2,7,1,2,14"}).toList();

        Day<Integer> day = new Day07();
        assertEquals(Integer.valueOf(37), day.part1(sample));
    }
}
