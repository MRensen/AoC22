//package nl.arjenwiersma.aoc.days;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//        List<Integer> inputList = getInputList();
//
//        long[] counters = new long[9];
//
//        for(int fish : inputList){
//            counters[fish]++;
//        }
//
//        for(int days = 256; days > 0; days--) {
//            long waitForEndOfDay = 0;
//            for (int fish = 0; fish < 9; fish++) {
//                // als de vis 0 is, dan maken we een nieuwe vis van 8 en zetten we de oude vis op 6
//                // als de vis geen 0 is, dan doen we -1
//                if (fish == 0) {
//                    waitForEndOfDay = counters[0];
//                } else {
//                    counters[fish-1] = counters[fish];
//                }
//            }
//            counters[8] = waitForEndOfDay;
//            counters[6] += waitForEndOfDay;
//        }
//
//        long sum = 0l;
//        for(long counter : counters){
//            sum += counter;
//        }
//        System.out.println(sum);
//
//        for(int days = 256; days > 0; days--) {
//            for (int fish = inputList.size() - 1; fish >= 0; fish--) {
//                Integer currentFish = inputList.get(fish);
//                // als de vis 0 is, dan maken we een nieuwe vis van 8 en zetten we de oude vis op 6
//                // als de vis geen 0 is, dan doen we -1
//                if (currentFish == 0) {
//                    inputList.add(8);
//                    inputList.set(fish, 6);
//                } else {
//                    inputList.set(fish, currentFish - 1);
//                }
//            }
//        }
//
//        System.out.println(inputList.size());
//
//    }
//
//
//
//
//
//
//    private static List<Integer> getInputList() {
//        List<Integer> inputList = new ArrayList<>();
//        File file = new File("src/nl/mark/input.txt");
//        try{
//            FileInputStream stream = new FileInputStream(file);
//            // Deze leest elke item gescheiden door een komma als unieke input
//            Scanner scanner = new Scanner(new FileInputStream((file)));
//            scanner.useDelimiter(",");
//            while(scanner.hasNext()){
//                inputList.add(Integer.parseInt(scanner.next()));
//            }
//            // Deze leest de enige input regel als regel
////            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
////            inputList = reader.lines().collect(Collectors.toList());
//        } catch(IOException e){
//            e.printStackTrace();
//        }
//        return inputList;
//    }
//}
