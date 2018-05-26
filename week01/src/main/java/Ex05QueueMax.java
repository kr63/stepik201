import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Ex05QueueMax {
    public static void main(String[] args) throws IOException {
        new Ex05QueueMax().run();
    }

    void run() throws IOException {
        QueueWithMax queueWithMax = new QueueWithMax();

        // read data
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(in.readLine());
        StringTokenizer stk = new StringTokenizer(in.readLine());
        int queueSize = Integer.parseInt(in.readLine());

        // move data to queueWithMax
        int elementsInQueue = 0;
        for (int i = 0; i < num; i++) {
            queueWithMax.push(Integer.parseInt(stk.nextToken()));
            elementsInQueue++;
            if (elementsInQueue >= queueSize) {
                System.out.println(queueWithMax.getCurrentMax());
                queueWithMax.pop();
                elementsInQueue--;
            }
        }
    }
}

class QueueWithMax {
    private StackWithMax leftStack = new StackWithMax();
    private StackWithMax rightStack = new StackWithMax();

    void push(int item) {
        leftStack.push(item);
    }

    void pop() {
        if (!rightStack.isEmpty()) {
            rightStack.pop();
        } else {
            while (!leftStack.isEmpty()) {
                rightStack.push(leftStack.peek());
                leftStack.pop();
            }
            rightStack.pop();
        }
    }

    int getCurrentMax() {
        int result = -1;
        if (!rightStack.isEmpty() && !leftStack.isEmpty()) {
            result = Integer.max(leftStack.getCurrentMax(), rightStack.getCurrentMax());
        }
        if (!leftStack.isEmpty() && rightStack.isEmpty()) {
            result = leftStack.getCurrentMax();
        }
        if (leftStack.isEmpty() && !rightStack.isEmpty()) {
            result = rightStack.getCurrentMax();
        }
        return result;
    }

    class StackWithMax {
        private LinkedList<Integer> valueStack = new LinkedList<>();
        private LinkedList<Integer> maxStack = new LinkedList<>();
        private int currentMax;

        void push(int item) {
            valueStack.push(item);
            currentMax = maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
            currentMax = Integer.max(currentMax, item);
            maxStack.push(currentMax);
        }

        void pop() {
            valueStack.pop();
            maxStack.pop();
        }

        int getCurrentMax() {
            currentMax = maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
            return currentMax;
        }

        boolean isEmpty() {
            return valueStack.isEmpty();
        }

        int peek() {
            return valueStack.peek();
        }
    }
}
