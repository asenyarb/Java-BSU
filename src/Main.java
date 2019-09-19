import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import static java.lang.Math.sqrt;

import TaylorSeries.TaylorSeries;


public class Main {

    public static void main(String[] args) {
        System.out.print("\nEnter x, belonging to ]-1,1[ : ");
        Scanner scan = new Scanner(System.in);
        BigDecimal x = scan.nextBigDecimal();
        System.out.print("Enter precision: ");
        BigInteger precision = scan.nextBigInteger();
        System.out.println();
        System.out.println("\n\nPrecised value is: " + new TaylorSeries(x).calculate(precision));
        System.out.print("Calculated value is: " + sqrt(1+x.doubleValue()));
    }
}
