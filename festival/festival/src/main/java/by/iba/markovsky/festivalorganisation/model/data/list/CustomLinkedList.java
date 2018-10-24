package by.iba.markovsky.festivalorganisation.model.data.list;

import java.util.Arrays;
import java.util.Iterator;

public class CustomLinkedList<E> extends CustomAbstractList<E> implements CustomList<E> {

    /**
     * One-side linked list
     */
    private class Node{

        private E element;
        private Node nextElement;

        public Node(E element){
            this.element = element;
            this.nextElement = null;
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
            return nextElement != null ? nextElement.equals(node.nextElement) : node.nextElement == null;
        }
        @Override
        public int hashCode() {
            int result = element != null ? element.hashCode() : 0;
            result = 31 * result + (nextElement != null ? nextElement.hashCode() : 0);
            return result;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", nextElement=" + nextElement +
                    '}';
        }

    }

    private Node firstNode;
    private Node lastNode;

    public CustomLinkedList() {
        this.firstNode = null;
        this.lastNode = null;
        this.size = EMPTY_SIZE;
    }
    public CustomLinkedList(E... element) {
        this.size = EMPTY_SIZE;
        addSeveral(element);
    }
    public CustomLinkedList(CustomLinkedList<E> customLinkedList) {
        this((E)customLinkedList.toArray());
    }
    public CustomLinkedList(CustomList<E> customList) {
        this((E)customList.toArray());
    }

    @Override
    public boolean add(E element) {
        if (isEmpty()) {
            firstNode = new Node(element);
            lastNode = firstNode;
        } else {
            Node newElement = new Node(element);
            lastNode.nextElement = newElement;
            lastNode = newElement;
        }
        ++size;
        return true;
    }

    @Override
    public boolean addSeveral(E[] element) {
        for (int i = 0; i < element.length; i++) {
            add(element[i]);
        }
        return true;
    }

    @Override
    public void clear() {
        while(firstNode != null){
            Node currentElement = firstNode.nextElement;
            firstNode.nextElement = null;
            firstNode.element = null;
            firstNode = currentElement;
        }
        firstNode = null;
        lastNode = null;
        size = EMPTY_SIZE;
    }

    @Override
    public Object[] toArray() {
        if (!isEmpty()) {
            Node currentElement = firstNode;
            Object[] elementArray = new Object[size];
            for (int i = 0; currentElement != null; i++, currentElement = currentElement.nextElement) {
                elementArray[i] = currentElement.element;
            }
            return elementArray;
        }
        return null;
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < EMPTY_SIZE) return null;

        E tempObj = null;
        if (index == 0) {
            Node secondElement = firstNode.nextElement;
            tempObj = firstNode.element;

            firstNode.nextElement = null;
            firstNode.element = null;
            firstNode = secondElement;
            --size;
        } else {
            Node currentElement = firstNode;
            for (int i = 0; currentElement != null; i++, currentElement = currentElement.nextElement) {
                if (i == index - 1) {
                    if(index == size - 1){ //If deleted object is last element
                        lastNode = currentElement;
                    }
                    Node deletedElement = currentElement.nextElement;
                    tempObj = deletedElement.element;

                    deletedElement.element = null;
                    currentElement.nextElement = deletedElement.nextElement;
                    deletedElement.nextElement = null;
                    --size;
                    break;
                }
            }
        }
        return tempObj;
    }

    @Override
    public E peek() {
        return lastNode.element;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if(index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < EMPTY_SIZE) return null;

        Node currentElement = firstNode;
        E tempObj = null;
        for (int i = 0; currentElement != null; i++, currentElement = currentElement.nextElement) {
            if (i == index) {
                tempObj = currentElement.element;
                break;
            }
        }
        return tempObj;
    }

    @Override
    public E set(int index, E element) {
        if (index >= size || index < EMPTY_SIZE) return null;

        Node currentElement = firstNode;
        E tempObj = null;
        for (int i = 0; currentElement != null; i++, currentElement = currentElement.nextElement) {
            if (i == index) {
                tempObj = currentElement.element;
                currentElement.element = element;
                break;
            }
        }
        return tempObj;
    }

    @Override
    public void add(int index, E element) {
        if (index >= size || index < EMPTY_SIZE) {
            add(element);
        } else if (index == 0) {
            Node newElement = new Node(element);
            newElement.nextElement = firstNode;
            firstNode = newElement;
            ++size;
        } else {
            Node newElement = new Node(element);
            Node currentElement = firstNode;
            for (int i = 0; currentElement != null; i++, currentElement = currentElement.nextElement) {
                if (i == index - 1) {
                    newElement.nextElement = currentElement.nextElement;
                    currentElement.nextElement = newElement;
                    ++size;
                    break;
                }
            }
        }
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) return -1;

        int index = -1;
        Node currentElement = firstNode;
        for (int i = 0; currentElement != null; i++, currentElement = currentElement.nextElement) {
            if (currentElement.element.equals(o)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) return -1;

        int index = -1;
        Node currentElement = firstNode;
        for (int i = 0; currentElement != null; i++, currentElement = currentElement.nextElement) {
            if (currentElement.element.equals(o)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public String allIndexOf(Object o) {
        if (o == null) return null;

        StringBuilder allIndex = new StringBuilder();
        Node currentElement = firstNode;
        for (int i = 0; currentElement != null; i++, currentElement = currentElement.nextElement) {
            if (currentElement.element.equals(o)) {
                allIndex.append(i + " ");
            }
        }
        return allIndex.toString().trim();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node currentElement = firstNode;

            @Override
            public boolean hasNext() {
                return currentElement != null;
            }

            @Override
            public E next() {
                Node tempObj = currentElement;
                currentElement = currentElement.nextElement;
                return tempObj.element;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomLinkedList<?> that = (CustomLinkedList<?>) o;

        if (firstNode != null ? !firstNode.equals(that.firstNode) : that.firstNode != null) return false;
        return lastNode != null ? lastNode.equals(that.lastNode) : that.lastNode == null;
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstNode != null ? firstNode.hashCode() : 0);
        result = 31 * result + (lastNode != null ? lastNode.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "CustomLinkedList{" +
                "list=" + Arrays.toString(toArray()) +
                ", size=" + size +
                '}';
    }

}
