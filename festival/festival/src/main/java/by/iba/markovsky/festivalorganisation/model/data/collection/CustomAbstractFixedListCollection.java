package by.iba.markovsky.festivalorganisation.model.data.collection;

import by.iba.markovsky.festivalorganisation.model.data.list.CustomLinkedList;
import by.iba.markovsky.festivalorganisation.model.data.list.CustomList;
import by.iba.markovsky.festivalorganisation.exception.CustomContainerEmptyException;

import java.util.Iterator;

/**
 Abstract class for fixed-size list containers.
 */
public class CustomAbstractFixedListCollection<E> extends CustomAbstractFixedCollection<E> implements CustomCollection<E> {

    protected CustomList<E> linkedList;

    public CustomAbstractFixedListCollection() {
        this.linkedList = new CustomLinkedList<>();
        this.containerSize = DEFAULT_SIZE;
        this.size = EMPTY_SIZE;
    }
    public CustomAbstractFixedListCollection(int size) {
        this.linkedList = new CustomLinkedList<>();
        this.containerSize = size;
        this.size = EMPTY_SIZE;
    }
    public CustomAbstractFixedListCollection(E... element) {
        this.linkedList = new CustomLinkedList<>(element);
        this.containerSize = element.length > DEFAULT_SIZE ? element.length : DEFAULT_SIZE;
        this.size = element.length;
    }

    @Override
    public void clear() {
        linkedList.clear();
        size = EMPTY_SIZE;
    }

    @Override
    public E peek() {
        return isEmpty() ? null : linkedList.peek();
    }

    @Override
    public Object[] toArray() {
        return linkedList.toArray();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentPos = size - 1;

            @Override
            public boolean hasNext() {
                return currentPos > -1 && linkedList.get(currentPos) != null;
            }

            @Override
            public E next() {
                return linkedList.get(currentPos--);
            }

            public int getCurrentPos(){
                return currentPos;
            }
        };
    }

    protected final E deleteElement() throws CustomContainerEmptyException {
        if(isEmpty()){
            throw new CustomContainerEmptyException();
        }
        return linkedList.remove(--size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomAbstractFixedListCollection<?> that = (CustomAbstractFixedListCollection<?>) o;

        return linkedList != null ? linkedList.equals(that.linkedList) : that.linkedList == null;
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (linkedList != null ? linkedList.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "CustomAbstractFixedListCollection{" +
                "linkedList=" + linkedList +
                "size=" + size +
                "containerSize=" + containerSize +
                '}';
    }

}
