package min_coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        char[][] m = new char[3][10];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                m[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 9; j >=0; j--) {
                if (m[i][j] >= 'A' && m[i][j] <= 'Z') {
                    System.out.printf("%s", m[i][j]);
                    break;
                }
            }
        }
        br.close();
    }
}
