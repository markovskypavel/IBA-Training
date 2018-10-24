package by.iba.markovsky.festivalorganisation.model.data.tree;

import by.iba.markovsky.festivalorganisation.model.data.collection.CustomAbstractCollection;
import by.iba.markovsky.festivalorganisation.exception.CustomTreeEmptyException;

import java.util.Iterator;

public class CustomBinaryTree<E extends Comparable<? super E>>
        extends CustomAbstractCollection<E> implements CustomTree<E>{

    private class Node{

        private E element;
        private Node left;
        private Node right;

        public Node(E element){
            this.element = element;
            this.left = null;
            this.right = null;
        }

        public E getElement(){
            return element;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (element != null ? !element.equals(node.element) : node.element != null) return false;
            if (left != null ? !left.equals(node.left) : node.left != null) return false;
            return right != null ? right.equals(node.right) : node.right == null;
        }
        @Override
        public int hashCode() {
            int result = element != null ? element.hashCode() : 0;
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return result;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

    }

    private Node root;

    public CustomBinaryTree() {
        this.root = null;
        this.size = EMPTY_SIZE;
    }
    @SuppressWarnings("unchecked")
    public CustomBinaryTree(E... element) {
        for (int i = 0; i < element.length; i++) {
            add(element[i]);
        }
    }
    public CustomBinaryTree(CustomBinaryTree<E> customBinaryTree) {
        this(customBinaryTree.toArray());
    }
    public CustomBinaryTree(CustomTree<E> customTree) {
        this(customTree.toArray());
    }

    @Override
    public void add(E element) {
        root = addRecursion(root, element);
        ++size;
    }
    private Node addRecursion(Node node, E element){
        if (node == null) {
            return new Node(element);
        } else if (element.compareTo(node.element) < 0) {
            node.left = addRecursion(node.left, element);
        } else {
            node.right = addRecursion(node.right, element);
        }
        return node;
    }

    @Override
    public boolean contains(E element) {
        return findElement(root, element) != null;
    }
    private Node findElement(Node node, E element){
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.element) == 0) {
            return node;
        }
        return (element.compareTo(node.element) < 0) ? findElement(node.left, element) : findElement(node.right, element);
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = EMPTY_SIZE;
    }

    @Override
    public void remove(E element) throws CustomTreeEmptyException {
        if(isEmpty()){
            throw new CustomTreeEmptyException();
        }
        removeRecursion(root, element);
    }
    private Node removeRecursion(Node current, E element){
        if (current == null) {
            return null;
        }
        if (element.compareTo(current.element) == 0) {
            //Situation, when leaf do not have children
            if (current.left == null && current.right == null) {
                return null;
            }
            //Situation, when leaf have one children
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            //Situation, when leaf have two children
            E smallestValue = findSmallestValue(current.right);
            current.element = smallestValue;
            current.right = removeRecursion(current.right, smallestValue);
            return current;
        }
        if (element.compareTo(current.element) < 0) {
            current.left = removeRecursion(current.left, element);
            return current;
        }
        current.right = removeRecursion(current.right, element);
        return current;
    }
    private E findSmallestValue(Node node) {
        return node.left == null ? node.element : findSmallestValue(node.left);
    }

    @Override
    public StringBuilder preOrder() throws CustomTreeEmptyException {
        if(isEmpty()){
            throw new CustomTreeEmptyException();
        }
        return preOrderRecursion(root, new StringBuilder());
    }
    private StringBuilder preOrderRecursion(Node node, StringBuilder stringBuilder){
        if(node != null){
            stringBuilder.append(node.element + " ");
            preOrderRecursion(node.left, stringBuilder);
            preOrderRecursion(node.right, stringBuilder);
        }
        return stringBuilder;
    }

    @Override
    public StringBuilder postOrder() throws CustomTreeEmptyException {
        if(isEmpty()){
            throw new CustomTreeEmptyException();
        }
        return postOrderRecursion(root, new StringBuilder());
    }
    private StringBuilder postOrderRecursion(Node node, StringBuilder stringBuilder){
        if(node != null){
            postOrderRecursion(node.left, stringBuilder);
            postOrderRecursion(node.right, stringBuilder);
            stringBuilder.append(node.element + " ");
        }
        return stringBuilder;
    }

    @Override
    public StringBuilder inOrder() throws CustomTreeEmptyException {
        if(isEmpty()){
            throw new CustomTreeEmptyException();
        }
        return inOrderRecursion(root, new StringBuilder());
    }
    private StringBuilder inOrderRecursion(Node node, StringBuilder stringBuilder){
        if(node != null){
            inOrderRecursion(node.left, stringBuilder);
            stringBuilder.append(node.element + " ");
            inOrderRecursion(node.right, stringBuilder);
        }
        return stringBuilder;
    }

    @Override
    public E[] toArray() throws UnsupportedOperationException  {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public E peek() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFull() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomBinaryTree<?> that = (CustomBinaryTree<?>) o;

        return root != null ? root.equals(that.root) : that.root == null;
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (root != null ? root.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "CustomBinaryTree{" +
                "root=" + root +
                "size=" + size +
                '}';
    }

}
