package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;
import nl.mrensen.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day09Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(9);

    @Test
    public void part1() {
        Day<Integer> day = new Day09();
        assertEquals(Integer.valueOf(5874), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day09();
        assertEquals(Integer.valueOf(2467), day.part2(input.getLines()));
    }
}
