/**
 * @author Rajarshi Mandal
 * @version 6.4.1
 * Solution for Exercise 6-4 of Think Java by Allen Downey
 */
 
public class MultAdd {

    public static double multAdd(double a, double b , double c){
        return a * b + c;
    }

    public static void message(String message){
        System.out.println(message);
    }

    public static double expSum(double x){

        double a, b, c;
        a = x;
        b = Math.exp(-x);
        c = Math.sqrt(1 - b);
        return multAdd(a, b, c);

    }

    public static void main(String args[]){

        // Write a main method that tests multadd by invoking it with a few simple parameters, like 1.0, 2.0, 3.0.
        message("Remember the main method is a * b + c!");
        message("So putting the value of a=1, b=2 and c=3 we get " + multAdd(1, 2, 3));
        message(" ");

        // Also in main, use multadd to compute the following values:
        // Trigonometry
        double a, b, c, calculateSin , calculateCos , divideBy2;
        calculateSin = Math.sin(Math.PI / 4);
        calculateCos = Math.cos(Math.PI / 4);
        divideBy2 = 1 / 2.0;

        message("When invoked with multAdd\n" +
                "calculateCos = a, divideBy2 = b, calculateSin = c:\n"
                + multAdd(calculateCos, divideBy2, calculateSin));
        message(" ");

        // Logarithm
        a = 1;
        b = Math.log(10);
        c = Math.log(20);
        message("Logarithm: " + multAdd(a, b, c));
        message(" ");

        // Exponent
        message("Exponent: " + expSum(a));

    }
}
