package Hippodrome;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Horse implements Serializable, Cloneable {
    private String name;
    private int age;
    private int experience;
    private String owner;
    private Date birth_date;

    private static final long serialVersionUID = 1L;

    public Horse(){}

    public Horse(String line) throws ParseException {
        var contents = line.split(",");
        this.name = contents[0];
        this.age = Integer.parseInt(contents[1]);
        this.experience = Integer.parseInt(contents[2]);
        this.owner = contents[3];
        this.birth_date = (new SimpleDateFormat("dd/MM/yyyy")).parse(contents[4]);
    }

    Horse(String name, int age, int experience, String owner, Date birth_date){
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.owner = owner;
        this.birth_date = birth_date;
    }

    public Horse clone() throws CloneNotSupportedException {
        super.clone();
        Horse h = new Horse();
        h.name = this.name;
        h.birth_date = this.birth_date;
        h.owner = this.owner;
        h.age = this.age;
        h.experience = this.experience;
        return h;
    }

    public String getName() {
        return name;
    }

    public byte[] getNameInByteArray(){
        StringBuilder sb = new StringBuilder(this.name);
        sb.setLength(20);
        return sb.toString().getBytes();
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public byte[] getBirth_dateInBytes() {
        StringBuilder sb = new StringBuilder((new SimpleDateFormat("dd/MM/yyyy")).format(birth_date));
        sb.setLength(10);
        return sb.toString().getBytes();
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }

    public String getOwner() {
        return owner;
    }

    public byte[] getOwnerInByteArray(){
        StringBuilder sb = new StringBuilder(this.owner);
        sb.setLength(20);
        return sb.toString().getBytes();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString(){
        return name + "," + age + "," + experience + "," +
                owner + "," + (new SimpleDateFormat("dd/MM/yyyy")).format(birth_date);
    }
}
