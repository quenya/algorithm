package exercise.DP;

import java.util.Scanner;

public class pelindromString {
    static String A;
    static int[] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextLine();
        map = new int[A.length()];
        sc.close();

        int s = 0, e = A.length() - 1;
        int mid = A.length() / 2;
        int count = 0;

        while (e != mid && s != mid) {
            if (A.charAt(s) == A.charAt(e)) {
                s++;
                e--;
                continue;
            }

            int sIndex = s;
            for (; sIndex < mid; sIndex++) if (A.charAt(sIndex) == A.charAt(e)) break;
            int sGap = sIndex - s;

            int eIndex = e;
            for (; eIndex > mid; eIndex--) if (A.charAt(s) == A.charAt(eIndex)) break;
            int eGap = e - eIndex;

            if (sGap > eGap) {
                count += eGap;
                e = eIndex;
                continue;
            } else if (sGap < sGap) {
                count += sGap;
                s = sIndex;
                continue;
            }

            count += sIndex;
            break;
        }
        System.out.println(count);
    }
}