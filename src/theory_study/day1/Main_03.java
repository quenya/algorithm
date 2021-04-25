package theory_study.day1;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_03 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        HashMap<Integer, Boolean> map = new HashMap<>();
        HashMap<Integer, String> employ = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int menu = Integer.parseInt(st.nextToken());
            if (menu == 1) {
                int number = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                if (map.containsKey(number)) {
                    bw.write(number + " ERROR\n");
                }
                else {
                    map.put(number, false);
                    employ.put(number, name);
                    bw.write(number + " OK\n");
                }
            }
            else if (menu == 2) {
                int number = Integer.parseInt(st.nextToken());
                if (map.containsKey(number)) {
                    boolean aBoolean = map.get(number);
                    if (aBoolean) {
                        map.put(number, false);
                        bw.write(number + " " + employ.get(number) + " EXIT\n");
                    }
                    else {
                        map.put(number, true);
                        bw.write(number + " " + employ.get(number) + " ENTER\n");
                    }
                }
                else {
                    bw.write(number + " ERROR\n");
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
