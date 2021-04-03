package exercise.greedy;

import java.util.Scanner;

public class max_degree2 {
    static int N;
    static Point[] P;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        P = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            P[i] = new Point(x, y);
        }
        sc.close();

        double maxDegree = 0;
        for (int i = N - 1; i > 0; i--) {
            Point p1 = P[i];
            Point p2 = P[i-1];
            int degree = Math.abs((p2.y - p1.y) / (p2.x - p1.x));
            if (maxDegree < degree)
                maxDegree = degree;
        }

        System.out.println(String.format("%.3f", maxDegree));
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
