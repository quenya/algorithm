package exercise.greedy;

import jdk.internal.dynalink.linker.ConversionComparator;

import java.util.*;

public class room2 {
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

        PriorityQueue<Room> roomPq = new PriorityQueue<>(N, Comparator.comparingInt(Room::getE).reversed());

        while (!M.isEmpty()) {
            Meeting meeting = M.poll();
            if (roomPq.isEmpty()) {
                Room room = new Room();
                room.addMeeting(meeting);
                roomPq.add(room);
            }
            else {
                boolean added = false;
                for (Room room : roomPq) {
                    if (room.e <= meeting.s) {
                        room.addMeeting(meeting);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    Room room = new Room();
                    room.addMeeting(meeting);
                    roomPq.add(room);
                }
            }
        }

        System.out.println(roomPq.stream().map(Room::getMeetingList).map(List::size).max(Integer::compareTo).orElse(0));
    }

    static class Room {
        List<Meeting> meetingList;
        int e;

        public List<Meeting> getMeetingList() {
            return meetingList;
        }

        public Room() {
            meetingList = new ArrayList<>();
        }

        public int getE() {
            return e;
        }

        public void addMeeting(Meeting meeting) {
            meetingList.add(meeting);
            e = meeting.e;
        }
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
