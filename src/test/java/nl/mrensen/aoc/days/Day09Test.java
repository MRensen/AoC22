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

    // python 1e helft: 417
    // python 2e helft: 2049
    // python 1990-2000: 32
    // python 1980-2000: 61
    // python 1970-2000: 101
    // python 1700-2000: 884
    // python 1800-1700: 262
    // python 1800-1750: 175
    // python 1700-1750: 93
    //1730-1740: 11  (alle andere stapjes van 10 tussen 1700 en 1750 geven hetzelfde resultaat)


    // java 1e helft: 431
    // java 2e helft: 2043
    // java 1990-2000: 32
    // java 1980-2000: 61
    // java 1970-2000: 101
    // java 1700-2000: 879
    // java 1800-1700: 258
    // java 1750-1800: 175
    // java 1700-1750: 89
    // 1730-1740: 10 (alle andere stapjes van 10 tussen 1700 en 1750 geven hetzelfde resultaat)

}
