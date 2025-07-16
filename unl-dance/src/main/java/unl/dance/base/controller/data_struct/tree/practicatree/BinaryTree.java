package unl.dance.base.controller.data_struct.tree.practicatree;

import unl.dance.base.controller.data_struct.list.LinkedList;

public class BinaryTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinaryTree() {
        this.root = null;
    }

    private void updateWeightAndHeight(Node<E> node) {
        if (node == null) return;
        Integer leftWeight = (node.left != null) ? node.left.weight : 0;
        Integer rightWeight = (node.right != null) ? node.right.weight : 0;
        node.weight = 1 + leftWeight + rightWeight;
        
        Integer leftHeight = (node.left != null) ? node.left.height : -1;
        Integer rightHeight = (node.right != null) ? node.right.height : -1;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    private Node<E> insertRecursive(Node<E> current, E data) {
        if (current == null) {
            return new Node<>(data);
        }
        if (data.compareTo(current.data) < 0) {
            Node<E> hijo = insertRecursive(current.left, data);
            current.left = hijo;
            hijo.header = current;
        } else if (data.compareTo(current.data) > 0) {
            Node<E> hijo = insertRecursive(current.right, data);
            current.right = hijo;
            hijo.header = current;
        }
        updateWeightAndHeight(current);
        return current;
    }

    public boolean insert(E data) {
        root = insertRecursive(root, data);
        return true;
    }

    private boolean containsRecursive(Node<E> current, E data) {
        if (current == null) return false;
        if (data.compareTo(current.data) == 0) return true;
        if (data.compareTo(current.data) < 0) return containsRecursive(current.left, data);
        return containsRecursive(current.right, data);
    }

    public boolean contains(E data) {
        return containsRecursive(root, data);
    }

    private void inOrderTraversalRecursive(Node<E> node, LinkedList<E> result) {
        if (node != null) {
            inOrderTraversalRecursive(node.left, result);
            result.add(node.data);
            inOrderTraversalRecursive(node.right, result);
        }
    }

    public LinkedList<E> inOrderTraversal() {
        LinkedList<E> result = new LinkedList<>();
        inOrderTraversalRecursive(root, result);
        return result;
    }

    public static void main(String[] args) {
        BinaryTree<String> arbol = new BinaryTree<>();
        arbol.insert("A");
        arbol.insert("B");
        arbol.insert("Z");
        arbol.insert("H");
        arbol.insert("C");
        arbol.insert("D");
        arbol.insert("E");

        String searchData = "Z";
        System.out.println("Data Contiene " + searchData + "? = " + arbol.contains(searchData));
        System.out.println("Data Inorden:");
        LinkedList<String> inOrderResult = arbol.inOrderTraversal();
        System.out.println(inOrderResult.print()); 

        System.out.println("--------------------------------------------------");
        BinaryTree<Integer> arbol2 = new BinaryTree<>();
        arbol2.insert(1);
        arbol2.insert(15);
        arbol2.insert(25);
        arbol2.insert(50);
        arbol2.insert(10);
        arbol2.insert(5);
        arbol2.insert(7);

        Integer searchData2 = 21;
        System.out.println("Data Contiene " + searchData2 + "? = " + arbol2.contains(searchData2));
        System.out.println("Data Inorden:");
        LinkedList<Integer> inOrderResult2 = arbol2.inOrderTraversal();
        System.out.println(inOrderResult2.print());
    }
}
