package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;
import nl.mrensen.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(05);

    @Test
    public void part1() {
        Day<String> day = new Day05();
        assertEquals("CMZ", day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<String> day = new Day05();
        assertEquals("MCD", day.part2(input.getLines()));
    }
}
