package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day04 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {
        //setup
//        input = Arrays.asList("2-4,6-8" ,
//                "2-3,4-5" ,
//                "5-7,7-9" ,
//                "2-8,3-7" ,
//                "6-6,4-6" ,
//                "2-6,4-8");
        List<Integer> l1, l2;
        int sum = 0;
        for(String s : input){
            int hyphen = s.indexOf("-");
            int hyphen2 = s.lastIndexOf("-");
            int comma = s.indexOf(",");
            l1 =
                    IntStream.rangeClosed( Integer.parseInt(s.substring(0,hyphen)),
                                           Integer.parseInt(s.substring(hyphen+1, comma)))
                    .boxed().toList();
           l2 =
                    IntStream.rangeClosed( Integer.parseInt(s.substring(comma+1,hyphen2)),
                                    Integer.parseInt(s.substring(hyphen2+1)))
                            .boxed().toList();
           //end setup
            if(Collections.indexOfSubList(l1,l2)>-1 || Collections.indexOfSubList(l2,l1)>-1){
                sum++;
            }
        }

        return sum;
    }

    @Override
    public Integer part2(List<String> input) {
        //setup
    //        input = Arrays.asList("2-4,6-8" ,
    //                "2-3,4-5" ,
    //                "5-7,7-9" ,
    //                "2-8,3-7" ,
    //                "6-6,4-6" ,
    //                "2-6,4-8");
        List<Integer> l1, l2;
        int sum = 0;
        for(String s : input){
            int hyphen = s.indexOf("-");
            int hyphen2 = s.lastIndexOf("-");
            int comma = s.indexOf(",");
            l1 = IntStream.rangeClosed( Integer.parseInt(s.substring(0,hyphen)),
                                    Integer.parseInt(s.substring(hyphen+1, comma)))
                            .boxed().toList();
            l2 = IntStream.rangeClosed( Integer.parseInt(s.substring(comma+1,hyphen2)),
                                    Integer.parseInt(s.substring(hyphen2+1)))
                            .boxed().toList();
            //end setup
            Set<Integer> merge = Stream.concat(l1.stream(),l2.stream()).collect(Collectors.toSet());
            sum+= merge.size()==l1.size()+l2.size()?0:1;

        }

        return sum;
    }
}
