package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

public class Main_06 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        int[] check = new int[200];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            check[c]++;
        }
        br.close();
        for (int i = 'A'; i <='Z'; i++) {
            if (check[i] > 0)
                bw.write(i);
        }
        bw.close();
    }

}
