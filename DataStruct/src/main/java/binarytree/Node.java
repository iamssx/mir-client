package binarytree;

import lombok.Data;

/**
 * Created by SSX on 2017/8/29.
 */
@Data
public class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private T value;
}
