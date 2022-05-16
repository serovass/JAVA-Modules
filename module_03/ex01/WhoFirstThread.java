package ex01;

enum Type {
    CONSUMER,
    PRODUCER
}

public class WhoFirstThread extends Thread implements Runnable {

    private final String msg;
    private final int printTimes;
    private final static Object lock = new Object();
    private final Enum<Type> type;
    private static Enum<Type> key = Type.CONSUMER;


    public WhoFirstThread(String msg, int printTimes, Enum<Type> type) {

        this.type = type;
        this.msg = msg;
        this.printTimes = printTimes;

    }

    @Override
    public void run() {

        for (int i = 0; i < this.printTimes; i++) {
            synchronized(lock) {

                while (key == this.type)
                {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(msg);
                key = this.type;
                lock.notifyAll();

            }
        }
    }


}

