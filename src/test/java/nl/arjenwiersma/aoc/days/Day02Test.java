package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;
import nl.arjenwiersma.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(02);

    @Test
    public void part1() {
        Day<Integer> day = new Day02();
        assertEquals(Integer.valueOf(2117664), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day02();
        assertEquals(Integer.valueOf(2073416724), day.part2(input.getLines()));
    }
}
