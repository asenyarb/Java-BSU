import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Random;
import static java.lang.Math.sqrt;

import TaylorSeries.TaylorSeries;


public class Main {

    public static void main(String[] args) {
        System.out.printf("Array of numbers:\n%11s %15s | %15s | %15s", "" , "decimal", "oct", "hex");
        int[] array_of_random_decimals = new int[5];
        Random random = new Random();
        Formatter formatter = new Formatter();
        for (int i=0; i<5; i++){
            array_of_random_decimals[i] = random.nextInt();
            formatter.format("\nElement %2d: %2$+(015d | %2$#-15o | %2$#15x", i+1, array_of_random_decimals[i]);
        }
        System.out.println(formatter);
        System.out.print("\nEnter x, belonging to ]-1,1[ : ");
        Scanner scan = new Scanner(System.in);
        BigDecimal x = scan.nextBigDecimal();
        System.out.print("Enter precision: ");
        BigInteger precision = scan.nextBigInteger();
        System.out.println();
        BigDecimal result = new TaylorSeries(x).calculate(precision);
        Formatter formatter2 = new Formatter();
        formatter2.format("%7.5f", result.doubleValue());
        System.out.println("\n\nPrecised value is: " + result);
        System.out.println("Same as float using formatter with .precision = 6: " + formatter2);
        System.out.print("Calculated value is: " + sqrt(1+x.doubleValue()));
    }
}
