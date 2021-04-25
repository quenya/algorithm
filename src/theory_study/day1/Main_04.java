package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

public class Main_04 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        String full = "";
        for (int i = 0; i < row; i++) {
            full += br.readLine();
        }
        int len = Integer.parseInt(br.readLine().trim());
        String s = br.readLine();
        br.close();
        for (int i = 0; i < full.length() - len+1; i++) {
            if (s.equals(full.substring(i, i+len))) {
                bw.write("(" + i / col + "," + i % col + ")\n");
            }
        }
        bw.close();
    }

}
