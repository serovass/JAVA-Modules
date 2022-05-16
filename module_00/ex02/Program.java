package ex02;
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

        int count = 0;

        while (scanner.hasNextInt()) {

            int number = scanner.nextInt();
            if (number > 1 && (number % 10) != 0) {

                if (number == 42)
                    break;

                int sum = 0;
                while (number > 0) {
                    sum += number % 10;
                    number /= 10;
                }

                boolean isPrime = true;

                for (int i = 2; i < mySqrt(sum); i++) {
                    if (sum % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime)
                    count++;
            }
        }
        System.out.println("Count of coffee-request - " + count);
    }
}
