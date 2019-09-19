package TaylorSeries;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;


public class TaylorSeries{
    private BigDecimal x;
    public TaylorSeries(BigDecimal _x) throws RuntimeException{
        if (_x.compareTo(BigDecimal.valueOf(-1.)) < 0 || _x.compareTo(BigDecimal.valueOf(1.)) > 0)
        {
            throw new RuntimeException("x should belong to (-1,1) !!!");
        }
        this.x = _x;
    }
    public BigDecimal calculate(BigInteger precision){
        BigDecimal denominator = new BigDecimal(10).pow(precision.intValue());
        BigDecimal epsilon = new BigDecimal(1).divide(denominator, precision.intValue(), RoundingMode.HALF_EVEN);
        BigDecimal element = this.x.divide(BigDecimal.valueOf(2), precision.intValue(), RoundingMode.HALF_EVEN);
        BigDecimal sum = new BigDecimal(1);
        BigInteger counter = BigInteger.ONE;
        BigDecimal el = new BigDecimal("-1");
        while (element.abs().compareTo(epsilon) > 0){
            sum = sum.add(new BigDecimal(-1).pow(counter.add(BigInteger.ONE).remainder(BigInteger.TWO).intValue()).multiply(element));
            el = el.add(new BigDecimal("2"));
            counter = counter.add(BigInteger.ONE);
            BigDecimal coefficient = el.multiply(this.x.divide(new BigDecimal(counter.multiply(BigInteger.TWO)), precision.intValue(), RoundingMode.HALF_EVEN)  );
            element = element.multiply(coefficient);
        }
        return (new BigDecimal(sum.toString(), new MathContext(precision.intValue()+1)));
    }
}
