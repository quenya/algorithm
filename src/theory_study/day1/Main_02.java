package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

public class Main_02 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        for (int i = 0; i < row; i++) {
            String s = br.readLine();
            int sum = 0;
            for (int j = 0; j < col; j++) {
                if ('1' == s.charAt(j))
                    sum++;
            }
            bw.write(sum + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
