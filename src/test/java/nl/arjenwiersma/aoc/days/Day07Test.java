package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;
import nl.arjenwiersma.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day07Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(05);

    @Test
    public void part1() {
        Day<Integer> day = new Day05();
        assertEquals(Integer.valueOf(5147), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day05();
        assertEquals(Integer.valueOf(0), day.part2(input.getLines()));
    }

    @Test
    public void sample(){
        List<String> sample = Arrays.stream(new String[]{}).toList();

        Day<Integer> day = new Day05();
        assertEquals(Integer.valueOf(5), day.part1(sample));
    }
}
