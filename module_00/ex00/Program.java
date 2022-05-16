package ex00;

public class Program {

    public static void main(String[] args) {

        int number = 479598;

        int sum = 0;
        int num = number;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        System.out.println(sum);
    }
}
