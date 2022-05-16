package ex01;
import java.util.Scanner;

public class Program {

    private static int mySqrt(int input) {

        int i = 0;
        while((i * i) < input){
            i++;
        }

        return i;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

        int n = scanner.nextInt();
        if (n <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

        boolean isPrime = true;
        int i;
        for (i = 2; i < mySqrt(n); i++)
        {
            if (n % i == 0)
            {
                isPrime = false;
                break;
            }
        }

        System.out.println(isPrime + " " + (i - 1));
    }
}
