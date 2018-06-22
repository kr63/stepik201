import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Ex02CheckTree {

    private int[] key;
    private int[] left;
    private int[] right;
    private int n;

    public static void main(String[] args) throws IOException {
        new Ex02CheckTree().run();
    }

    void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(in.readLine());

        // read data
        n = Integer.parseInt(stk.nextToken());
        key = new int[n];
        left = new int[n];
        right = new int[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(in.readLine());
            key[i] = Integer.parseInt(stk.nextToken());
            left[i] = Integer.parseInt(stk.nextToken());
            right[i] = Integer.parseInt(stk.nextToken());
        }

        if (check()) System.out.println("CORRECT");
        else System.out.println("INCORRECT");

    }

    private boolean check() {
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        if (n == 0) return true;
        if (test(stack, current)) return false;
        int previous = stack.peek();

        while (stack.size() > 0) {
            current = stack.pop();
            if (key[current] < key[previous]) return false;
            previous = current;

            if (right[current] != -1) {
                current = right[current];
                if (test(stack, current)) return false;
            }
        }
        return true;
    }

    private boolean test(Stack<Integer> stack, int current) {
        while (current != -1) {
            stack.push(current);
            if (left[current] != -1) {
                if (key[left[current]] == key[current]) return true;
            }
            current = left[current];
        }
        return false;
    }
}
