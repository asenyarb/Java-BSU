import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.*;

class Functions {

    static void console_line_matches(Regex reg, Scanner in){
        System.out.println("\nEnter the line:");
        String line = in.nextLine();
        if (reg.match(line))
            System.out.println("The line matches a regular expression");
        else
            System.out.println("The line doesn't match a regular expression");
    }

    static void file_lines_match(Regex reg, Scanner in) throws IOException {
        System.out.println("Enter an input file path. ('input.txt' by default)");
        String path = in.nextLine();
        System.out.println();
        if (path.equals(""))
            path = System.getProperty("user.dir") + "\\input.txt";
        else if(!path.contains("\\"))
            path = System.getProperty("user.dir") + "\\" + path;
        BufferedReader file_in;
        try {
            file_in = new BufferedReader(new FileReader(path));
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
            return;
        }
        String line;
        int line_counter = 0;
        StringBuilder buffer = new StringBuilder();
        while ((line = file_in.readLine()) != null){
            line_counter++;
            buffer.append("Line ").append(line_counter).append(": ").append(line);
            if (reg.match(line))
                buffer.append("\n\tMatches\n");
            else
                buffer.append("\n\tDoesn't match\n");
        }
        System.out.println(buffer);
        System.out.println("Save result to file? [yes(1)/no(0)]");
        if (yes_no_question(in))
        {
            System.out.println("Enter a name of the file to save result in ('output.txt' by default):");
            String filename = in.nextLine();
            if (filename.equals(""))
                filename = "output.txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(buffer.toString());
            out.close();
            System.out.println("Result is successfully saved.");
        }
    }

    static boolean yes_no_question(Scanner in){
        String answer;
        while (true) {
            answer = in.nextLine();
            answer = answer.toUpperCase();
            if (answer.equals("NO") || answer.equals("N") || answer.equals("0"))
                return false;
            else if (answer.equals("YES") || answer.equals("Y") || answer.equals("1")) {
                System.out.println();
                return true;
            }
            else
                System.out.println("Unrecognized answer. Try again.");
        }
    }

    static int read_number(Scanner in){
        int read_from;
        while (true){
            try {
                read_from = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException ex){
                System.out.println("The value you entered is not a number! Try again.");
                continue;
            }
            if (read_from < 0 || read_from > 2)
                System.out.println("Invalid operation number. Try again.");
            else
                break;
        }
        return read_from;
    }
}
