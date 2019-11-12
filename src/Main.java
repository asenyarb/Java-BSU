import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Regex reg = new Regex();
        Scanner in = new Scanner(System.in);
        while (true)
        {
            System.out.println("1. Check for consistency\n2. Change regex pattern\n0. Exit\n");
            int ans = Functions.read_number(in);
            switch(ans) {
                case 1:
                    System.out.println("1. Read lines from file\n2. Read a line from console\n0. Exit");
                    int read_from = Functions.read_number(in);
                    switch (read_from) {
                        case 1:
                            Functions.file_lines_match(reg, in);
                            break;
                        case 2:
                            Functions.console_line_matches(reg, in);
                            break;
                        case 0:
                            System.exit(0);
                    }
                    System.out.println("\nWould you like to continue? [yes(1)/no(0)]");
                    if(!Functions.yes_no_question(in))
                        System.exit(0);
                    break;
                case 2:
                    System.out.println("Enter a new regular expression pattern");
                    reg.change_pattern(in.nextLine());
                    System.out.println();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
