package pl.com.bottega.homework.fraction;


import pl.com.bottega.homework.polynomian.PolynomialFunction;

public class FractionTest {
    public static void main(String[] args){
        Fraction f1 = Fraction.create(1, 2);
        Fraction f2 = Fraction.create(1, 4);

        Fraction sum = f1.add(f2);
        Fraction inversed1 = f1.inverse();
        Fraction division = f1.divide(f2);

        String s = "123";
        Double d = Double.valueOf(s);
    }
}
