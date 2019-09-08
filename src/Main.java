import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;
import java.lang.RuntimeException;

import static java.lang.Math.sqrt;

public class Main {

    public static void main(String[] args) {
        System.out.print("\nEnter x, belonging to [-1,1[ : ");
        Scanner scan = new Scanner(System.in);
        double x = scan.nextDouble();
        System.out.print("Enter precision: ");
        int precision = scan.nextInt();
        System.out.println();
        System.out.println("\n\nPrecised value is: " + new Serie(x).calculate(precision));
        System.out.print("Calculated value is: " + sqrt(1+x));
    }
}

class Serie{
    private double x;
    Serie(double _x) throws RuntimeException{
        if (_x < -1. || _x> 1.){
            throw new RuntimeException("x should belong to [-1,1) !!!");
        }
        this.x= _x;
    }
    BigDecimal calculate(int precision){
        BigDecimal denominator = new BigDecimal(10).pow(precision);
        BigDecimal epsilon = new BigDecimal(1).divide(denominator, precision, RoundingMode.HALF_EVEN);
        BigDecimal element = new BigDecimal(this.x / 2 );
        BigDecimal sum = new BigDecimal(1);
        int counter = 1;
        int el = -1;
        while (element.compareTo(epsilon) > 0){
            sum = sum.add(new BigDecimal(-1).pow((counter+1)%2).multiply(element));
            el += 2;
            counter += 1;
            BigDecimal coefficient = new BigDecimal((el * this.x) / (2*counter)  );
            element = element.multiply(coefficient);
        }
        return (new BigDecimal(sum.toString(), new MathContext(precision)));
    }
}
