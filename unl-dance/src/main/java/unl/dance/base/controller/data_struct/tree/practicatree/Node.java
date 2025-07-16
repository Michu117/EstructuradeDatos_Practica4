package unl.dance.base.controller.data_struct.tree.practicatree;

public class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;
    Node<E> header; // nodo padre
    Integer weight; // peso del nodo
    Integer height; // altura del nodo

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.header = null; // inicialmente no tiene padre
        this.weight = 1;
        this.height = 0;
    }
}

