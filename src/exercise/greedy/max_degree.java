package exercise.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class max_degree {
    static int N;
    static Point[] p;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            p = new Point[N];
        }
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            p[i] = new Point(x, y);
        }
        sc.close();

        List<Point> pointList = Arrays.stream(p)
                .sorted(Comparator.comparing(Point::getX)
                .thenComparing(Point::getY))
                .collect(Collectors.toList());

        double maxDegree = 0;
        for (int i = 0; i < N-1; i++) {
            Point a = pointList.get(i);
            Point b = pointList.get(i + 1);
            double degree = Math.abs((a.y - b.y) / (a.x - b.x));
            if (maxDegree < degree) {
                maxDegree = degree;
            }
        }

        System.out.println(maxDegree);
    }

    static class Point {
        int x;
        int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
