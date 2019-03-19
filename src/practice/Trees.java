package practice;

import java.util.*;

public class Trees {
    static class Node {
        public Node(int data) {
            this.data = data;
        }

        int  data;
        Node left;
        Node right;
    }

    public static int height(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    public static void topView(Node root) {
        List<Integer> valList = new ArrayList<>();
        Node walk = root;
        while (walk.left != null) {
            walk = walk.left;
            valList.add(walk.data);
        }

        for (int i = valList.size() - 1; i >= 0; i--) {
            System.out.print(valList.get(i) + " ");
        }
        System.out.print(root.data + " ");

        walk = root;
        while (walk.right != null) {
            walk = walk.right;
            System.out.print(walk.data + " ");
        }
    }

    public static void preOrder(Node root) {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node cur = nodeStack.pop();
            System.out.print(cur.data + " ");
            if (cur.right != null) nodeStack.push(cur.right);
            if (cur.left != null) nodeStack.push(cur.left);
        }
    }

    public static void inOrder(Node root) {
        Stack<Node> nodeStack = new Stack<>();
        List<Integer> valueList = new ArrayList<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node cur = nodeStack.pop();
            valueList.add(cur.data);
//            System.out.print(cur.data + " ");
            if (cur.right != null) nodeStack.push(cur.right);
            if (cur.left != null) nodeStack.push(cur.left);
        }
        Collections.sort(valueList);
        for (Integer integer : valueList) {
            System.out.print(integer + " ");
        }
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }
}
