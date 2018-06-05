import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Ex02ParallelWork {
    private static final Comparator<Proc> BY_TIME_THEN_BY_ID =
            Comparator
                    .comparing(Proc::getTimeWhenProcWillBeFree)
                    .thenComparing(Proc::getProcId);

    public static void main(String[] args) throws IOException {
        new Ex02ParallelWork().run();
    }

    void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk1 = new StringTokenizer(in.readLine());
        int numProc = Integer.parseInt(stk1.nextToken());
        int numWork = Integer.parseInt(stk1.nextToken());
        Queue<Proc> queue = new PriorityQueue<>(BY_TIME_THEN_BY_ID);
        for (int i = 0; i < numProc; i++) {
            Proc proc = new Proc(i, 0L);
            queue.add(proc);
        }

        StringTokenizer stk2 = new StringTokenizer(in.readLine());
        for (int i = 0; i < numWork; i++) {
            int time = Integer.parseInt(stk2.nextToken());
            Proc proc = queue.poll();
            System.out.println(Objects.requireNonNull(proc).procId + " " + proc.timeWhenProcWillBeFree);
            proc.timeWhenProcWillBeFree += time;
            queue.add(proc);
        }
    }

    private class Proc {
        private int procId;
        private Long timeWhenProcWillBeFree;

        Proc(int procId, Long timeWhenProcWillBeFree) {
            this.procId = procId;
            this.timeWhenProcWillBeFree = timeWhenProcWillBeFree;
        }

        int getProcId() {
            return procId;
        }

        void setProcId(int procId) {
            this.procId = procId;
        }

        Long getTimeWhenProcWillBeFree() {
            return timeWhenProcWillBeFree;
        }

        void setTimeWhenProcWillBeFree(Long timeWhenProcWillBeFree) {
            this.timeWhenProcWillBeFree = timeWhenProcWillBeFree;
        }

        @Override
        public String toString() {
            return "Proc{" +
                    "procId=" + procId +
                    ", timeWhenProcWillBeFree=" + timeWhenProcWillBeFree +
                    '}';
        }
    }
}
