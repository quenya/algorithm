package theory_study.day1;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_08 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int[] people = new int[100_001];
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < width; j++) {
                int a = Integer.parseInt(st.nextToken());
                people[a]++;
            }
        }
        st = new StringTokenizer(br.readLine().trim());
        int bHeight = Integer.parseInt(st.nextToken());
        int bWidth = Integer.parseInt(st.nextToken());
        int b = 0;
        boolean[] check = new boolean[100_001];
        for (int i = 0; i < bHeight; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < bWidth; j++) {
                int black = Integer.parseInt(st.nextToken());
                if (!check[black]) {
                    b += people[black];
                }
                check[black] = true;
            }
        }
        br.close();

        bw.write(b + "\n" + (height * width - b));
        bw.close();
    }

}
