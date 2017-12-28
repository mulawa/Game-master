package pl.com.bottega.homework.fraction;

public class Fraction implements Comparable<Fraction>{
    private final long nominator;
    private final long denominator;

    private Fraction(long nominator, long denominator) {
        this.nominator = nominator;
        this.denominator = denominator;
    }

    public static Fraction create(long nominator, long denominator){
        if (denominator == 0)
            return null;
        return new Fraction(nominator, denominator);
    }


    public Fraction add(Fraction f2) {
        long newNominator = this.nominator * f2.denominator + f2.nominator * this.denominator;
        long newDenominator = this.denominator * f2.denominator;
        return new Fraction(newNominator, newDenominator);
    }

    public Fraction inverse() {
        return new Fraction(denominator, nominator);
    }

    public long getNominator() {
        return nominator;
    }

    public long getDenominator() {
        return denominator;
    }

    public Fraction divide(Fraction f2) {
        return multiple(f2.inverse());
    }

    public Fraction multiple(Fraction f2) {
        return new Fraction(this.nominator * f2.nominator, this.denominator * f2.denominator);
    }

    @Override
    public int compareTo(Fraction o) {
        return 0;
    }
}
