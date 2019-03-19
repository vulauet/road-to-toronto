package practice;

import java.util.*;

public class InorderTraversal {
    static class Node {
        Node(int data) {
            this.data = data;
        }
        int  data;
        Node left;
        Node right;
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
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
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
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        inOrder(root);
    }
}
