import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Ex03Network {
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
        int arriveTime, duration;
        int timeWhenProcWillBeFree = 0;

        for (int i = 0; i < number; i++) {
            // read arrive time & duration of the packet
            StringTokenizer stk = new StringTokenizer(in.readLine());
            arriveTime = Integer.parseInt(stk.nextToken());
            duration = Integer.parseInt(stk.nextToken());

            // check the queue size. If queue is full drop packet
            if (queue.size() == size && arriveTime < queue.getLast()) {
                System.out.println(-1);
                continue;
            }
            // arriveTime >= timeWhenProcWillBeFree --> proc isFree
            if (arriveTime >= timeWhenProcWillBeFree) {
                startTime = arriveTime;
                timeWhenProcWillBeFree = arriveTime + duration;
                queue.addLast(timeWhenProcWillBeFree);
            } else {
                startTime = timeWhenProcWillBeFree;
                timeWhenProcWillBeFree += duration;
                queue.addLast(timeWhenProcWillBeFree);
            }

            if (queue.getFirst() <= arriveTime) {
                queue.removeFirst();
            }

            System.out.println(startTime);
        }
    }
}
