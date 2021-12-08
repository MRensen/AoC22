package nl.arjenwiersma.aoc.days;

import nl.arjenwiersma.aoc.common.Day;
import nl.arjenwiersma.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day08Test {
        @Rule
        public DayInputExternalResource input = new DayInputExternalResource(8);

        @Test
        public void part1() {
            Day<Integer> day = new Day08();
            assertEquals(Integer.valueOf(409), day.part1(input.getLines()));
        }

        @Test
        public void part2() {
            Day<Integer> day = new Day07();
            assertEquals(Integer.valueOf(0), day.part2(input.getLines()));
        }

        @Test
        public void sample(){
            List<String> sample = Arrays.stream(new String[]{"be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe" ,
                    "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc " ,
                    "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg " ,
                    "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb " ,
                    "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea " ,
                    "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb " ,
                    "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe " ,
                    "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef " ,
                    "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb " ,
                    "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"}).toList();

            Day<Integer> day = new Day08();
            assertEquals(Integer.valueOf(26), day.part1(sample));
        }
}
