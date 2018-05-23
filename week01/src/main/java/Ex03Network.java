import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ex03Network {
    private Deque<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        new Ex03Network().run(in);
    }

    void run(BufferedReader in) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(in.readLine());
        int size = Integer.parseInt(stringTokenizer.nextToken());
        int number = Integer.parseInt(stringTokenizer.nextToken());
        if (number == 0) return;

        int startTime;
        int arriveTime, duration, timeWhenProcWillBeFree;

        for (int i = 0; i < number; i++) {
            // read arrive time & duration of the packet
            StringTokenizer stk = new StringTokenizer(in.readLine());
            arriveTime = Integer.parseInt(stk.nextToken());
            duration = Integer.parseInt(stk.nextToken());

            // drop packet if queue full & timeWhenProcWillBeFree > arriveTime
            if (queue.size() == size && arriveTime < queue.getFirst()) {
                System.out.println(-1);
                continue;
            }

            try {
                startTime = queue.removeLast();
                System.out.println(startTime);
                timeWhenProcWillBeFree = startTime + duration;
                queue.addLast(timeWhenProcWillBeFree);
            } catch (NoSuchElementException nse) {
                // if queue is empty --> startTime is arriveTime
                System.out.println(arriveTime);
                timeWhenProcWillBeFree = arriveTime + duration;
                queue.addLast(timeWhenProcWillBeFree);
            }
        }
    }
}
