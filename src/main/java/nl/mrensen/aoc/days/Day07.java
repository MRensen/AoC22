package nl.mrensen.aoc.days;

import com.google.common.collect.Maps;
import nl.mrensen.aoc.common.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 implements Day<Integer> {
    int line = 0;
    Map<String, Dir> dirs = new HashMap();
    Dir current;
    record Dir(String name, Dir parent, List<Dir> children, List<File> files){};
    record File(Dir container, int size, String name){};

    private void parseCom(String input){
        String command = input.substring(2,4).trim();
        String rest = input.substring(4).trim();
//        System.out.println(rest);
        if(command.equals("cd") && rest.equals("/")){
            if(!dirs.containsKey("null")){
                Dir d = new Dir("null", null, new ArrayList<>(), new ArrayList<>());
                dirs.put("null", d);
            }
            current = dirs.get("null");
        } else if(command.equals("cd") && rest.equals("..")) {
            if(current!= null) {
                current = current.parent;
            } else {
//                System.out.println("current is empty: " + current);
            }
        } else if(command.equals("cd")){
            if(!dirs.containsKey(rest)){
                throw new RuntimeException("Dir does not exist: " + input);
            }
            Dir d = dirs.get(rest);
            current = d;
        } else if(command.equals("ls")){

        }
    }

    private void parseDir(String input){
//        System.out.println(input);
        String[] split = input.split(" ");
        String name = split[1];
        createDir(name);
    }

    private void createDir(String name) {
        if(!dirs.containsKey(name)){
            Dir d = new Dir(name, current, new ArrayList<>(), new ArrayList<>());
            current.children.add(d);
            dirs.put(name, d);
        } else {
//            System.out.println("Dir already crated");
        }
    }

    private void parseFile(String input){
//        System.out.println(input);
        String[] split = input.split(" ");
        Integer size = Integer.valueOf(split[0]);
        String name = split[1];
        File f = new File(current, size, name);
        current.files.add(f);

    }

    private Integer getDirSize(Dir dir){
        Integer size = 0;
        for(Dir d: dir.children()){
            if(!d.children.isEmpty()){
                for(Dir c : d.children){
                    size += getDirSize(d) + getFilesSize(c.files);
                }
//                size += d.children.stream().mapToInt(this::getDirSize).sum();
            }
            size += getFilesSize(d.files);
        }
        size += getFilesSize(dir.files);
        return size<=100000?size:0;
//        dirs.forEach((i,j)->{return ;});
    }

    private Integer getFilesSize(List<File> files){
        Integer size = files.stream().mapToInt(e->e.size).sum();
        return size;
    }

    private String printDirs(Dir dir, int counter){
        StringBuilder sb = new StringBuilder();
        sb.append("Dir: " + dir.name + " ("+ counter +")" + "\n");
        for(Dir d: dir.children()){
            sb.append(printDirs(d, counter++));
        }
        return sb.toString();
    }

    @Override
    public Integer part1(List<String> input) {

        for(String s : input){
            if(s.startsWith("$")){
                parseCom(s);
            } else if(s.startsWith("dir")){
                parseDir(s);
            } else {
                parseFile(s);
            }
            line++;
        }
//        System.out.println(printDirs(dirs.get("null"),0));

        return dirs.values().stream().mapToInt(this::getDirSize).sum();
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
