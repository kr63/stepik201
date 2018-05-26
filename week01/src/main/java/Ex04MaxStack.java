import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Ex04MaxStack {
    public static void main(String[] args) throws IOException {
        new Ex04MaxStack().run();
    }

    void run() throws IOException {
        StackWithMax stackWithMax = new StackWithMax();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(in.readLine());
            switch (stk.nextToken()) {
                case "push":
                    stackWithMax.push(Integer.parseInt(stk.nextToken()));
                    break;
                case "max":
                    System.out.println(stackWithMax.getCurrentMax());
                    break;
                case "pop":
                    stackWithMax.pop();
                    break;
            }
        }
    }
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
}
