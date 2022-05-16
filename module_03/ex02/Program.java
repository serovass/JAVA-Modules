package ex02;

import java.util.Random;

public class Program {

    public static void main(String[] args) {

        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {

            System.err.println("Usage: java Program --arraySize=<arraySize> --threadsCount=<threadsCount>");
            System.exit(-1);
        }

        int arraySize = 0;
        int threadsCount = 0;
        try {
            arraySize = Integer.parseInt(args[0].substring(12));
            threadsCount = Integer.parseInt(args[1].substring(15));

        } catch (NumberFormatException e) {
            System.err.println("Usage: arraySize, threadsCount > 0 and <= 2000000");
            System.exit(-1);
        }

        if ( arraySize <= 0 || threadsCount <= 0 ||  arraySize > 2000000 || threadsCount > 2000000) {
            System.err.println("Usage: arraySize, threadsCount > 0 and <= 2000000");
            System.exit(-1);
        }
        else if (arraySize < threadsCount)
        {
            System.err.println("Usage: arraySize >= threadsCount");
            System.exit(-1);
        }

        Integer [] intArr = new Integer[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {

            intArr[i] = random.nextInt(1000);
        }

        int sum = 0;
        for (int i = 0; i < arraySize; i++) {
            sum += intArr[i];
        }
        System.out.println("Sum: " + sum);


        CountThread[] threadArr = new CountThread[threadsCount];
        int elementsCount = arraySize / threadsCount;

        int start = 0;
        int end = 0;
        for (int i = 0; i < threadsCount && start < arraySize; i++) {

            if (elementsCount == 1)
                end = start;
            else
                end = start + elementsCount;

            if (i == threadsCount - 1 || end >= arraySize) {
                threadArr[i] = new CountThread(i + 1, intArr, start , arraySize - 1);
            }
            else {
                threadArr[i] = new CountThread(i + 1, intArr, start , end);
            }
            threadArr[i].start();
            start = end + 1;
        }

        for(int i = 0; i < threadsCount; i++) {

            try {
                threadArr[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum by threads: " + threadArr[0].getSumAll());

    }
}
