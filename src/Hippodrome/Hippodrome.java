package Hippodrome;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Hippodrome {
    private ArrayList<Horse> horses;

    public Hippodrome() {
        horses = new ArrayList<Horse>();
    }
    public Hippodrome(ArrayList<Horse> horses_list){
        horses = new ArrayList<Horse>(horses_list);
    }

    public void addHorse(Horse horse){
        try {
            horses.add(horse.clone());
        }catch (CloneNotSupportedException e){
            System.out.println("Clone not supported!");
        }
    }

    public Horse[] filter(Map<String, Object> attrs_map){
        List<Horse> found_horses = new LinkedList<Horse>();
        for (var horse: this.horses){
            if (attrs_map.containsKey("name"))
                if (!horse.getName().equals(attrs_map.get("name")))
                    continue;
            if (attrs_map.containsKey("age"))
                if (horse.getAge() != (int)attrs_map.get("age"))
                    continue;
            if (attrs_map.containsKey("experience"))
                if (horse.getExperience() != (int)attrs_map.get("experience"))
                    continue;
            if (attrs_map.containsKey("owner"))
                if (horse.getOwner() != attrs_map.get("age"))
                    continue;
            if (attrs_map.containsKey("birth_date"))
                if (horse.getBirth_date() != attrs_map.get("birth_date"))
                    continue;
            found_horses.add(horse);
        }
        return (Horse[])found_horses.toArray();
    }

    public void toTxtFile(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (var horse: horses){
            sb.append(horse.toString()).append("\n");
        }
        BufferedWriter f = new BufferedWriter(new FileWriter(path));
        f.write(sb.toString());
        f.close();
    }

    public void fromTxtFile(String path) throws IOException{
        horses = new ArrayList<Horse>();
        BufferedReader f = new BufferedReader(new FileReader(path));
        String line;
        while ((line = f.readLine()) != null){
            try{
                horses.add(new Horse(line));
            }catch (ParseException e){
                Logger logger = Logger.getLogger("Hippodrome");
                logger.setLevel(Level.WARNING);
                logger.warning("Parse_exception for '" + line + "'\nLine shall be skipped");
            }
        }
    }

    public void toBinaryFile(String path) throws IOException{
        FileOutputStream objectOutputStream = new FileOutputStream(path);
        for (var horse: horses){
            objectOutputStream.write(horse.getNameInByteArray());
            byte byteAge = Integer.valueOf(horse.getAge()).byteValue();
            objectOutputStream.write(byteAge);
            objectOutputStream.write(Integer.valueOf(horse.getExperience()).byteValue());
            objectOutputStream.write(horse.getOwnerInByteArray());
            objectOutputStream.write(horse.getBirth_dateInBytes());
            // 50
        }
        objectOutputStream.close();
    }

    public void fromBinaryFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream oi = new FileInputStream(path);
        horses = new ArrayList<Horse>();
        Horse horse;
        while(true) {
            horse = new Horse();
            try {
                horse.setName(new String(oi.readNBytes(20)).trim());
                horse.setAge(oi.read());
                horse.setExperience(oi.read());
                horse.setOwner(new String(oi.readNBytes(20)).trim());
                String date_str = new String(oi.readNBytes(10));
                Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(date_str);
                horse.setBirth_date(birthDate);
                horses.add(horse);
            } catch (ArrayIndexOutOfBoundsException | ParseException e) {
                break;
            }
        }
        oi.close();
    }

    public void toXMLSheet(String path){
        try{
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
            for (var horse: this.horses)
                encoder.writeObject(horse);
            //encoder.writeObject(this.horses);
            encoder.close();
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File");
        }
    }

    public void fromXMLSheet(String path) throws FileNotFoundException {
        XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
        horses = new ArrayList<Horse>();
        Horse horse;
        while(true) {
            try {
                horse = (Horse) d.readObject();
                horses.add(horse);
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        d.close();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for (var horse: this.horses){
            str.append(horse).append("\n");
        }
        return str.toString();
    }

    public int changeAgeInBinFile(String horseName, Integer newAge) throws IOException {
        /**
         * Returns previous horse age
         */
        int positionToSeek = 0;
        int old_age = -1;
        for (var horse: horses){
            if (horse.getName().equalsIgnoreCase(horseName)){
                positionToSeek += 20;
                old_age = horse.getAge();
                break;
            }
            positionToSeek += 52;
        }
        if (old_age == -1) {
            System.out.println("Horse with that name hasn't been found!");
            return old_age;
        }
        RandomAccessFile rf = new RandomAccessFile("Hippodrome.bin", "rw");
        rf.seek(positionToSeek);
        rf.write(newAge.byteValue());
        rf.close();
        return old_age;
    }
}
