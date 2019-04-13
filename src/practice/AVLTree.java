package practice;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class AVLTree {
    private static class Node {
        int  val;
        int  ht;
        Node left;
        Node right;

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node();
            root.val = val;
            return root;
        }

        if (val < root.val)
            root.left = insert(root.left, val);
        else if (val > root.val)
            root.right = insert(root.right, val);
        else
            return root;

        root.ht = updateHeight(root);
        int balanceFactor = getBalanceFactor(root);

        if (balanceFactor > 1 && val < root.left.val) {
            return rightRotate(root);
        }

        if (balanceFactor < -1 && val > root.right.val) {
            return leftRotate(root);
        }

        if (balanceFactor > 1 && val > root.left.val) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balanceFactor < -1 && val < root.right.val) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private static Node rotate(
            Node root,
            Function<Node, Node> oldRootGetter,
            Function<Node, Node> newRootGetter,
            BiConsumer<Node, Node> oldRootSetter,
            BiConsumer<Node, Node> newRootSetter
    ) {
        Node newRoot = oldRootGetter.apply(root);
        Node childNode = newRootGetter.apply(newRoot);

        newRootSetter.accept(newRoot, root);
        oldRootSetter.accept(root, childNode);

        root.ht = updateHeight(root);
        newRoot.ht = updateHeight(newRoot);
        return newRoot;
    }

    private static Node functionalRightRotate(Node root) {
        return rotate(root, Node::getLeft, Node::getRight, Node::setLeft, Node::setRight);
    }

    private static Node functionalLeftRotate(Node root) {
        return rotate(root, Node::getRight, Node::getLeft, Node::setRight, Node::setLeft);
    }

    private static Node rightRotate(Node root) {
        Node newRoot = root.left;
        Node right0 = newRoot.right;

        // rotate
        newRoot.right = root;
        root.left = right0;

        // update height
        root.ht = updateHeight(root);
        newRoot.ht = updateHeight(newRoot);
        return newRoot;
    }

    private static Node leftRotate(Node root) {
        Node newRoot = root.right;
        Node left0 = newRoot.left;

        // rotate
        newRoot.left = root;
        root.right = left0;

        // update height
        root.ht = updateHeight(root);
        newRoot.ht = updateHeight(newRoot);
        return newRoot;
    }

    private static int updateHeight(Node root) {
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private static int height(Node node) {
        return node == null ? 0 : node.ht;
    }

    private static int getBalanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
}
