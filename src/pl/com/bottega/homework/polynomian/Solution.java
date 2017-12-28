package pl.com.bottega.homework.polynomian;


import java.util.Scanner;

public class Solution {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        //pobrac paramtery
        double a = readParameter("a");
        double b = readParameter("b");
        double c = readParameter("c");

        //wyswietlic rownanie
        System.out.println(generateGeneralForm(a, b, c));

        //wyznaczenie delty (wyroznic trojmiany kwadratowego)
        double delta = Math.pow(b, 2) - 4*a*c;

        //3 przypadki
        if (delta == 0){
            System.out.println("ZERO");
        }
    }

    private static String generateGeneralForm(double a, double b, double c) {
        String bSign = b>=0 ? "+" : "";
        String cSign = c>=0 ? "+" : "";
        return a + "x^2 " + bSign + b + "x" + cSign + c;
    }

    private static double readParameter(String coefficient) {
        System.out.print("Podaj " + coefficient + ": ");
        return scanner.nextDouble();
    }
}
