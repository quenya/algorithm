package exercise.DP;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class distanceString {
    static String A;
    static String B;
    static int[] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextLine();
        B = sc.nextLine();
        if (A.length() < B.length()) {
            String temp = A;
            A = B;
            B = temp;
        }
        map = new int[A.length()];
        sc.close();

        List<Character> aList = A.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        List<Character> bList = B.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

        for (int i = 0; i < aList.size(); i++) {
            if (aList.get(i) == bList.get(i))
                map[i] = 0;
            else {
                map[i] = 1;
                bList.remove(i);
                if (bList.size() > i) break;
            }
        }

        int dist = Arrays.stream(map).boxed().mapToInt(i -> i).sum() + Math.abs(bList.size() - aList.size());

        System.out.println(dist);
    }
}
