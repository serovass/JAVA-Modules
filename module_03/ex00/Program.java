package ex00;

public class Program {

    public static void main(String[] args) {

        if (args.length != 1 || !args[0].startsWith("--count=")) {

            System.err.println("Usage: java Program --count=<times to print>");
            System.exit(-1);
        }

        int printTimes = Integer.parseInt(args[0].substring(8));
        if (printTimes > 0) {

            WhoFirstThread egg = new WhoFirstThread("Egg", printTimes);
            WhoFirstThread hen = new WhoFirstThread("Hen", printTimes);
            egg.start();
            hen.start();

            try {
                hen.join();
                egg.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < printTimes; i++) {
                System.out.println("Human");
            }
        }
    }
}
