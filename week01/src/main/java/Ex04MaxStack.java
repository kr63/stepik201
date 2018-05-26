import java.util.LinkedList;
import java.util.Scanner;

class Ex04MaxStack {
    public static void main(String[] args) {
        new Ex04MaxStack().run();
    }

    private void run() {
        StackWithMax stackWithMax = new StackWithMax();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            String command = in.next();
            switch (command) {
                case "push":
                    stackWithMax.push(in.nextInt());
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
