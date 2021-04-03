package exercise.greedy;

import java.util.*;

public class room {
    static int N;
    static PriorityQueue<Meeting> M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = new PriorityQueue<>(N, Comparator.comparingInt(o -> o.s));
        for (int i = 0; i < N; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            M.add(new Meeting(s, e));
        }
        sc.close();

        PriorityQueue<Integer> roomPq = new PriorityQueue<>(N, Collections.reverseOrder());

        while (!M.isEmpty()) {
            Meeting meeting = M.poll();
            if (roomPq.isEmpty()) {
                roomPq.add(meeting.e);
            }
            else {
                int index = -1;
                for (Integer i : roomPq) {
                    if (i <= meeting.s) {
                        index = i;
                        break;
                    }
                }
                if (index >= 0) {
                    roomPq.remove(index);
                }
                roomPq.add(meeting.e);
            }
        }

        System.out.println(roomPq.size());
    }

    static class Meeting {
        int s;
        int e;

        public Meeting(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}
