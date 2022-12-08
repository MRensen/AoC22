package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;
import nl.mrensen.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(99);

    @Test
    public void part1() {
        Day<Integer> day = new Day08();
        assertEquals(Integer.valueOf(21), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day08();
        assertEquals(Integer.valueOf(7), day.part2(input.getLines()));
    }
}
