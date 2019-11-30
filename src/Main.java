import Hippodrome.Hippodrome;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Hippodrome hip = new Hippodrome();
        hip.fromTxtFile("Hippodrome.txt");
        //hip.toXMLSheet("xml_text.xml");
        //System.out.println(hip);
        //hip.fromXMLSheet("xml_text.xml");
        hip.toBinaryFile("Hippodrome.bin");
        hip.changeAgeInBinFile("Klava", 23);
        hip.fromBinaryFile("Hippodrome.bin");
        System.out.println(hip);
    }
}
