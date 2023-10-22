package nl.mrensen.aoc.days;

import nl.mrensen.aoc.common.Day;
import org.checkerframework.checker.units.qual.A;

import java.util.*;



public class Day07 implements Day<Integer> {
    int line = 0;
    HashMap<String, Integer> goodDirs = new HashMap<>();
    HashMap<String, Integer> allDirs = new HashMap<>();
    Dir home;
    Dir current;

    private void parseCom(String input){
        String command = input.substring(2,4).trim();
        String rest = input.substring(4).trim();

        // "CD /" -> Zet de "home" dir als de huidige dir.
        if(command.equals("cd") && rest.equals("/")){
            if(home == null){
                // Set home
                home = new Dir("null", null, new ArrayList<>(), new ArrayList<>());
            }
            current = home;
        }

        // "CD .." -> De parent van de huidige dir wordt de huidige dir
        else if(command.equals("cd") && rest.equals("..")) {
            if(current!= home) {
                current = current.parent;
            } else {
                System.out.println("current is home: " + current);
            }
        }
        // "CD [naam van dir]" -> De child dir met de jusite naam, wordt de huidige dir.
        else if(command.equals("cd")){
            Dir moveTo = null;
            // Door zoek de namen van de childrenDirs en ga naar de childDir die overeen komt met de naam in het CD command
            for(Dir d : current.children){
                if(d.name.equals(rest)){
                    moveTo = d;
                }
            }
            if(moveTo == null){
                // Hier zou je niet moeten kunnen komen
                throw new RuntimeException("Dir does not exist as co-sibling of parent: " + input);
            }
            current = moveTo;
        } else if(command.equals("ls")){

        }
    }

    private void parseDir(String input){
        String[] split = input.split(" ");
        String name = split[1];
        createDir(name);
    }

    private void createDir(String name) {
        // check of de parent als een dir heeft met deze naam
        if(current.children.stream().noneMatch((e)->name.equals(e.getName()))){
            //zo niet, maak dan deze dir aan en voeg het toe aan de children lijst van de parent.
            Dir d = new Dir(name, current, new ArrayList<>(), new ArrayList<>());
            current.children.add(d);
        } else {
            // Als het goed is kun je hier niet komen, want een LS laat alle dirs van een parent zien, geen dubbele
            System.out.println("Dir already crated (should not be possible) -> line:" + lineCounter);
        }
    }

    private void parseFile(String input){
        // maak een file en voeg toe aan de parent
        String[] split = input.split(" ");
        Integer size = Integer.valueOf(split[0]);
        String name = split[1];
        File f = new File(current, size, name);
        current.files.add(f);

    }

    private Integer getDirSize(Dir dir){
        // Deze methode gebruikt recursie om de size van een dir te bepalen.
        Integer size = 0;
        // loop door alle child-dirs heen
        for(Dir d: dir.children){
            // voor elke child-dir, roep deze methode opnieuw aan.
            // Returned pas tot het bij een childmap zonder eigen children komt.
            size += getDirSize(d);
        }
        // voeg de filesizes toe aan de totale size van deze dir
        size += dir.files.stream().mapToInt(e->e.size).sum();

        // Als het totaal onder de 100000, voldoet het aan de eis van de opdracht.
        if(size <= 100000){
            if(goodDirs.containsKey(dir)){throw new RuntimeException("DOUBLE KEY IN MAP IS IMPOSSIBLE");}
            // Omdat "goodDirs" een Map is, moet de key uniek zijn. Daarom voegen we een unieke identifier toe aan de dirNaam.
            goodDirs.put(dir.name +"("+totalDirs+")", size);
        }

        allDirs.put(dir.name +"("+totalDirs+")", size); //handig voor debuggen
        totalDirs ++; //handig voor debuggen

        // return de size (dit is ook belangrijk voor de recursie.)
        return size;
    }

    int lineCounter = 0;
    int totalDirs = 0;
    @Override
    public Integer part1(List<String> input) {

        // Loop door alle regels van de input heen
        for(String s : input){
            lineCounter ++; // handig voor debuggen
            // Er zijn drie mogelijke inputs: COMMAND, DIR en FILE
            if(s.startsWith("$")){
                parseCom(s);
            } else if(s.startsWith("dir")){
                parseDir(s);
            } else {
                parseFile(s);
            }
            line++;
        }

        // Berekend de size van alle dirs.
        getDirSize(home);
        return goodDirs.values().stream().mapToInt(Integer::intValue).sum();
    }


    @Override
    public Integer part2(List<String> input) {

        // Loop nogmaals door alle regels van de input heen om de file structuur weer op te zetten
        for(String s : input){
            lineCounter ++; // handig voor debuggen
            // Er zijn drie mogelijke inputs: COMMAND, DIR en FILE
            if(s.startsWith("$")){
                parseCom(s);
            } else if(s.startsWith("dir")){
                parseDir(s);
            } else {
                parseFile(s);
            }
            line++;
        }

        // Hoeveel ruimte hebben we nu gebruikt?
        // Dit vult ook meteen de "allDirs" hashMap.
        int currentUsedDiscSize =  getDirSize(home);

        // Dit zijn de waardes vanuit de opdracht
        int TOTALDISCSPACE = 70000000;
        int NEEDEDDISCSPACE = 30000000;

        // Een lijst met alle sizes van alle dirs (we hebben de namen van dedirs niet nodig)
        ArrayList<Integer> allDirSizes = new ArrayList<>(allDirs.values());

        // sorteer de sizes van laag naar hoog
        Collections.sort(allDirSizes);

            for(int i : allDirSizes){
                int freeSpace = TOTALDISCSPACE - currentUsedDiscSize + i;
                if (freeSpace >= NEEDEDDISCSPACE){

                    // Return de eerste size die voldoende ruimte maakt
                    return i;
                }
            }

            // Als er geen resultaat gevonden wordt, return je null. De test faalt dan.
        return null;
    }

    private class File{
        Dir container;
        int size;
        String name;

        public File(Dir container, int size, String name) {
            this.container = container;
            this.size = size;
            this.name = name;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return size == file.size && Objects.equals(container, file.container) && Objects.equals(name, file.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(container.name, size, name);
    }

    @Override
    public String toString() {
        return "File{" +
                "container=" + container.name +
                ", size=" + size +
                ", name='" + name +
                '}';
    }
}
    private class Dir{
        String name;
        Dir parent;
        List<Dir> children;
        List<File> files;

        public Dir(String name, Dir parent, List<Dir> children, List<File> files) {
            this.name = name;
            this.parent = parent;
            this.children = children;
            this.files = files;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dir dir = (Dir) o;
            return Objects.equals(name, dir.name) && Objects.equals(parent, dir.parent) && Objects.equals(children, dir.children) && Objects.equals(files, dir.files);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, parent.name, children.size(), files.size());
        }

        @Override
        public String toString() {
            return "Dir{" +
                    "name='" + name + '\'' +
                    ", parent=" + parent.name +
                    ", children=" + children.size() +
                    ", files=" + files.size() +
                    '}';
        }
    }
}
