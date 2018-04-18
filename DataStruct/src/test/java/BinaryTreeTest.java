import binarytree.BinaryTree;
import binarytree.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * Created by SSX on 2017/8/29.
 */
public class BinaryTreeTest {

    @Test
    public void printTreeByLevelTest() {
        BinaryTree binaryTree = new BinaryTree();
        Node<Integer> head = new Node<>();
        head.setValue(1);
        Node<Integer> left = new Node<>();
        left.setValue(2);
        Node<Integer> right = new Node<>();
        right.setValue(3);
        head.setLeft(left);
        head.setRight(right);
        binaryTree.printTreeByLevel(head);
    }

    @Test
    public void sortQueue() {
        PriorityQueue<FireWork> queue = new PriorityQueue<>();
        FireWork fireWork = new FireWork(1, 200, 1);
        queue.offer(new FireWork(1, 100, 1));
        queue.offer(fireWork);
        System.out.println(queue.peek());
        fireWork.setUseTime(10);
        System.out.println(queue.peek());

    }


}

@Data
@AllArgsConstructor
class FireWork implements Comparable<FireWork> {
    private long playerId;
    int useTime;
    long lastUse;

    @Override
    public int compareTo(FireWork o) {
        if (o == null) {
            return 0;
        }
        if (useTime > o.useTime) {
            return 1;
        }
        if (useTime == o.useTime) {
            return lastUse < o.lastUse ? 1 : -1;
        }
        return 0;
    }
}
