package ex00;

public class WhoFirstThread extends Thread {

    private final String msg;
    private final int printTimes;


    public WhoFirstThread(String msg, int printTimes) {

        this.msg = msg;
        this.printTimes = printTimes;
    }

    @Override
    public void run() {

        for (int i = 0; i < this.printTimes; i++) {
            System.out.println(msg);
        }

    }

}

