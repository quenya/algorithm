package min_coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class serving {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String readLine = br.readLine();
            StringTokenizer st = new StringTokenizer(readLine);
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            String line = br.readLine();
            if (line.length() > R && line.length() - 1 - R >= 0)
                line = line.substring(line.length() - R) + line + line.substring(0, R);
            String[] foods = line.split("");
            boolean satisfied = true;

            for (int i = 0; i < N; i++) {
                HashMap<String, Integer> countMap = initMap(foods);
                for (int j = i; j < i + R * 2 + 1; j++) {
                    if (j >= foods.length) break;
                    String food = foods[j];
                    countMap.put(food, countMap.get(food) + 1);
                    if (countMap.get(food) > 2) {
                        satisfied = false;
                        break;
                    }
                }
                if (!satisfied) break;
            }

            System.out.println("#" + test_case + " " + ((satisfied) ? "YES" : "NO"));
        }
        br.close();
    }

    private static HashMap<String, Integer> initMap(String[] foods) {
        return Arrays.stream(foods).collect(Collectors.toMap(food -> food, food -> 0, (a, b) -> b, HashMap::new));
    }
}
