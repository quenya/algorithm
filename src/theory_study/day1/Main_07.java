package theory_study.day1;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_07 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        Set<Integer> people = new HashSet<>();
        int[] multi = new int[1000 * 1000];
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < width; j++) {
                int a = Integer.parseInt(st.nextToken());
                people.add(a);
                multi[a]++;
            }
        }
        st = new StringTokenizer(br.readLine().trim());
        int bHeight = Integer.parseInt(st.nextToken());
        int bWidth = Integer.parseInt(st.nextToken());
        int b = 0;
        Set<Integer> check = new HashSet<>();
        for (int i = 0; i < bHeight; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < bWidth; j++) {
                int black = Integer.parseInt(st.nextToken());
                if (people.contains(black) && !check.contains(black)) {
                    b += multi[black];
                }
                check.add(black);
            }
        }
        br.close();

        bw.write(b + "\n" + (height * width - b));
        bw.close();
    }

}
