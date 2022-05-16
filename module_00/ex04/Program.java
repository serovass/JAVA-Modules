package ex04;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.equals("")) {
            System.exit(0);
        }

        char[] charArr = line.toCharArray();
        char [][] outCharArr = new char[12][10];
        int [] intArr = new int[line.length()];
        char [] letterArr = new char[line.length()];

        int arrLen = -1;
        for (int i = 0; i < line.length(); i++) {

            int j;
            for (j = 0; j < line.length() && charArr[i] != '\n'; j++) {
                if (letterArr[j] == charArr[i]) {
                    intArr[j]++;
                    break ;
                }
            }
            if (j == line.length()) {
                arrLen++;
                letterArr[arrLen] = charArr[i];
				if (intArr[arrLen] != 999) {
					intArr[arrLen]++;
				}
            }
        }

        double step = (double)intArr[getMaxInt(intArr, arrLen)] / 10;
        int [] outIntArr = new int[10];

        for (int l = 0; l < 10 && l <= arrLen; l++)
        {
            int index = getMaxInt(intArr, arrLen);

            outCharArr[11][l] = letterArr[index];
            int steps = (int)((double)intArr[index] / step);

            for (int i = 10; i >= 0; i--)
            {
                if (steps > 0)
                    outCharArr[i][l] = '#';
                else if (steps == 0)
                    outCharArr[i][l] = 'n';
                else
                    outCharArr[i][l] = ' ';
                steps--;
            }
            outIntArr[l] = intArr[index];
            intArr[index] = -1;

        }

        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j <= arrLen && j < 10; j++) {

                if (i != 11 && outCharArr[i][j] == 'n') {
                    System.out.format("%3d", outIntArr[j]);
                }
                else {
                    System.out.print("  " + outCharArr[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static int getMaxInt(int [] intArr, int arrLen) {

        int n = intArr[0];
        int j = 0;
        for(int i = 0; i <= arrLen; i++)
        {
            if (n < intArr[i])
            {
                n = intArr[i];
                j = i;
            }
        }

        return j;
    }
}
