import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ex01TreeTraversal {
    public static void main(String[] args) throws IOException {

        new Ex01TreeTraversal().run();
    }

    void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(in.readLine());

        // create tree
        Tree tree = new Tree();
        int n = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(in.readLine());
            tree.insert(Integer.parseInt(stk.nextToken()));
            int left = Integer.parseInt(stk.nextToken());
            if (left != -1) {
                tree.insert(left);
            }
            int right = Integer.parseInt(stk.nextToken());
            if (right != -1) {
                tree.insert(right);
            }
        }

        tree.inOrder(tree.root);
    }

    private class Tree {
        private Node root;

        Tree() {
            this.root = null;
        }

        void inOrder(Node localRoot) {
            if (localRoot == null) return;
            inOrder(localRoot.left);
            System.out.print(localRoot.key + " ");
            inOrder(localRoot.right);
        }

        void insert(int key) {
            Node node = new Node();
            node.key = key;

            if (root == null) {
                root = node;
            } else {
                Node current = root;
                Node parent;
                while (true) {
                    parent = current;
                    if (key < current.key) {
                        current = current.left;
                        if (current == null) {
                            parent.left = node;
                            return;
                        }
                    } else {
                        current = current.right;
                        if (current == null) {
                            parent.right = node;
                            return;
                        }
                    }
                }
            }
        }
    }

    private class Node {
        int key;
        Node left;
        Node right;
    }
}
