import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Ex01TreeTraversal {

    private int[] key;
    private int[] left;
    private int[] right;

    public static void main(String[] args) throws IOException {

        Ex01TreeTraversal ex01TreeTraversal = new Ex01TreeTraversal();
        ex01TreeTraversal.run();

    }

    void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(in.readLine());

        // create tree
        int n = Integer.parseInt(stk.nextToken());
        key = new int[n];
        left = new int[n];
        right = new int[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(in.readLine());
            key[i] = Integer.parseInt(stk.nextToken());
            left[i] = Integer.parseInt(stk.nextToken());
            right[i] = Integer.parseInt(stk.nextToken());
        }

        List<Integer> inOrder = inOrder();
        print(inOrder);
    }

    private void print(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int item: list) {
            sb.append(item).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        pushAll(stack, 0);

        while (!stack.isEmpty()) {
            int top = stack.pop();
            result.add(key[top]);
            top = right[top];
            pushAll(stack, top);
        }
        return result;
    }

    private void pushAll(Stack<Integer> stack, int root) {
        while (root != -1) {
            stack.push(root);
            root = left[root];
        }
    }

}
