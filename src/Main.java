import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please, enter matrix sizes: ");
        int n = in.nextInt();
        int m = in.nextInt();
        Matrix matrix = new Matrix();
        try {
            matrix = new Matrix(n, m);
            matrix.fillWithRandomValues();
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        System.out.println("\nInitial matrix:");
        matrix.out();


        System.out.println("\nMatrix with the minimum element in the lower right position");
        matrix.minElementInTheLowerRightAngle();
        System.out.println("Matrix after modification:");
        matrix.out();

        System.out.println("\nEnter 'from' and 'to' indexes to partially sort the 1st row");
        int fromIndex = in.nextInt();
        int toIndex = in.nextInt();
        try {
            matrix.partialSort(fromIndex, toIndex);
            System.out.println("\nSorted matrix:");
            matrix.out();
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
            System.out.println("Array row hasn't been sorted");
        }

        matrix.sort();
        System.out.println("\nSorted matrix:");
        matrix.out();

        System.out.println("\nBinary search\nEnter the value to search:");
        List<Integer> coordinates = matrix.find(in.nextFloat());
        System.out.println("\nRow index: " + coordinates.get(0) + "\tColumn index: " + coordinates.get(1));

        in.close();
    }
}
