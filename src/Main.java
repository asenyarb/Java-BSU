import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Calendar calendar = new GregorianCalendar();
        System.out.println("Using Calendar.get:");
        System.out.println("Current time:");
        System.out.println("\tMonth - " + (long)(calendar.get(Calendar.MONTH)+1));
        System.out.println("\tHours - " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("\tMinutes - " + calendar.get(Calendar.MINUTE));
        calendar.add(Calendar.HOUR, 2);
        System.out.println("Current time +2 hours:\t\t\t\t" + calendar.getTime());
        calendar.add(Calendar.MONTH, 10);
        System.out.println("Time +10 months:\t\t\t\t\t" + calendar.getTime());
        calendar = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy");
        System.out.println("Using SimpleDateFormat");
        System.out.println("Year: " + df.format(calendar.getTime()));
        SimpleDateFormat month_english_repr = new SimpleDateFormat("EEE", new Locale("English"));
        SimpleDateFormat month_russian_repr = new SimpleDateFormat("EEEE", new Locale("Ru"));
        System.out.println("Month russian representation: " + month_russian_repr.format(calendar.getTime()));
        System.out.println("Month english representation: " + month_english_repr.format(calendar.getTime()));
        SimpleDateFormat dmzl = new SimpleDateFormat("dddd");
        System.out.println("The day of month with zeros leading: " + dmzl.format(calendar.getTime()));
        SimpleDateFormat ttw = new SimpleDateFormat("K");
        System.out.println("Hour in am/pm (0-11) without zeros leading: " + ttw.format(calendar.getTime()));
        SimpleDateFormat mwoz = new SimpleDateFormat("m");
        System.out.println("Minutes without zeros leading: " + mwoz.format(calendar.getTime()));
        Formatter fm = new Formatter();
        System.out.println("Using formatter:");
        System.out.println(fm.format("Full month name: %TB", calendar.getTime()));
        Formatter fm2 = new Formatter();
        System.out.println(fm2.format("Day of month, formatted as two digits, i.e. 1 - 31: %Te", calendar.getTime()));
        Formatter fm3 = new Formatter();
        System.out.println(fm3.format("Hours in locale specific 12-hours format: %Tl%tp", calendar.getTime(), calendar.getTime()));
        Formatter fm4 = new Formatter();
        System.out.println(fm4.format("Seconds as two digits with a leading zero: %TS", calendar.getTime()));

    }
}
