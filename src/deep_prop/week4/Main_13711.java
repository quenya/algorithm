package deep_prop.week4;

import java.io.*;
import java.util.*;

public class Main_13711 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(Integer.parseInt(st.nextToken()), i);
        }
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = map.get(Integer.parseInt(st.nextToken()));
        }

        Vector<Integer> vector = new Vector<>();
        for (int i : arr) {
            if (vector.isEmpty() || vector.lastElement() < i) vector.add(i);
            else vector.set(-Collections.binarySearch(vector, i) - 1, i);
        }

        bw.write(vector.size() + "");
        bw.close();
        br.close();
    }
}
