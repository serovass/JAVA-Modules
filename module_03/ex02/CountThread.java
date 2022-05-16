package ex02;


public class CountThread extends Thread implements Runnable {

    private final int id;
    private final Integer[] arr;
    private final int start;
    private final int end;
    private int sum;
    private static int sumAll = 0;


    public CountThread(int id, Integer[] arr, int start, int end) {

        this.id = id;
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.sum = 0;
    }

    private void addSumAll(int sum) {
        sumAll += sum;
    }

    public int getSumAll() {
        return sumAll;
    }

    @Override
    public void run() {

        for (int i = this.start; i <= this.end; i++)
        {
            synchronized(this) {
                this.sum += this.arr[i];
            }
        }

        synchronized(this) {
            addSumAll(sum);
            System.out.println("Thread " + this.id + ": from " + this.start + " to " + this.end + " sum is " + this.sum);
            }
    }


}

