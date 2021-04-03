package min_coding.G3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class finger {
    static int len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        len = Integer.parseInt(br.readLine());
        Queue<String> command = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            command.add(br.readLine());
        }
        br.close();
        run(command, 5, 5);
    }

    static void run(Queue<String> command, int y, int x) {
        if (command.isEmpty())
            return;
        String poll = command.poll();
        switch (poll) {
            case "up":
                run(command, y-1, x);
                break;
            case "down":
                run(command, y+1, x);
                break;
            case "left":
                run(command, y, x-1);
                break;
            case "right":
                run(command, y, x+1);
                break;
            case "click":
                System.out.printf("%d,%d\n",y,x);
                run(command, y, x);
                break;
        }
    }
}
