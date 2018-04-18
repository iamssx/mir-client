package binarytree;


import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    /**
     * 按层打印二叉树
     *
     * @param head
     */
    public void printTreeByLevel(Node<Integer> head) {
        if (head == null) {
            System.out.println("---- end -----");
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        Node<Integer> lastNode = head;//当前行最右节点
        Node<Integer> nLastNode = head;//下一行最右节点
        queue.add(head);
        while (queue.size() != 0) {
            Node<Integer> poll = queue.poll();
            System.out.print(poll.getValue() + " ");
            if (poll.getLeft() != null) {
                queue.offer(poll.getLeft());
                nLastNode = poll.getLeft();
            }
            if (poll.getRight() != null) {
                queue.offer(poll.getRight());
                nLastNode = poll.getRight();
            }
            if (poll == lastNode) {
                System.out.println();
                lastNode = nLastNode;
            }
        }
    }
    @Test
    public void printTreeByLevelTest() {
        Node<Integer> head = new Node<>();
        head.setValue(1);
        Node<Integer> left = new Node<>();
        left.setValue(2);
        Node<Integer> right = new Node<>();
        right.setValue(3);
        head.setLeft(left);
        head.setRight(right);
        Node<Integer> node = new Node<>();
        node.setValue(7);
        left.setRight(node);
        node = new Node<>();
        node.setValue(9);
        right.setRight(node);
        printTreeByLevel(head);
    }


}

