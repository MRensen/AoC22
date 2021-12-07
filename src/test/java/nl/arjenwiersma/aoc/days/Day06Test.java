package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;
import nl.arjenwiersma.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day06Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(06);

    @Test
    public void part1() {
        Day<Long> day = new Day06();
        assertEquals(Long.valueOf(360268), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Long> day = new Day06();
        assertEquals(Long.valueOf(1632146183902L), day.part2(input.getLines()));
    }

    @Test
    public void sample(){
        List<String> sample = Arrays.stream(new String[]{"3,4,3,1,2"}).toList();

        Day<Long> day = new Day06();
        assertEquals(Long.valueOf(5934), day.part1(sample));
        assertEquals(Long.valueOf(26984457539L), day.part2(sample));
    }
}
