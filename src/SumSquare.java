import java.util.ArrayList;
import java.util.Scanner;

public class SumSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //총 몇개의 숫자를 입력 받을 것인지
        int n;
        int m;
        int q;
        long e[][];
        n = sc.nextInt();
        m = sc.nextInt();

        q = sc.nextInt();

        e = new long[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int thisN = sc.nextInt();

                long left;
                if (j > 0) left = e[i][j - 1];
                else left = 0;

                long up;
                if (i > 0) up = e[i - 1][j];
                else up = 0;

                long sideUP;
                if (i > 0 && j > 0) sideUP = e[i - 1][j - 1];
                else sideUP = 0;

                e[i][j] = left + up + thisN - sideUP;
            }
        }

        System.out.println("");

        for (int i = 0; i < q; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            long bigBox = e[x2][y2];

            long rightBox;
            if (y1 > 0) rightBox = e[x2][y1 - 1];
            else rightBox = 0;

            long upBox;
            if (x1 > 0) upBox = e[x1 - 1][y2];
            else upBox = 0;

            long sideUPBox;
            if (x1 > 0 && y1 > 0) sideUPBox = e[x1 - 1][y1 - 1];
            else sideUPBox = 0;

            System.out.println(bigBox - rightBox - upBox + sideUPBox);
        }

        sc.close();
    }
}
