package by.iba.markovsky.festivalorganisation.data.collection;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomContainerEmptyException;

import java.util.Arrays;
import java.util.Iterator;

public abstract class CustomAbstractFixedArrayCollection<E> extends CustomAbstractFixedCollection<E> implements CustomCollection<E> {

    protected Object[] fixedArray;

    public CustomAbstractFixedArrayCollection() {
        super();
        this.fixedArray = new Object[this.containerSize = DEFAULT_SIZE];
        this.size = EMPTY_SIZE;
    }
    public CustomAbstractFixedArrayCollection(int size) {
        super();
        this.fixedArray = new Object[this.containerSize = size];
        this.size = EMPTY_SIZE;
    }
    public CustomAbstractFixedArrayCollection(E... element) {
        super();
        this.fixedArray = element.length > DEFAULT_SIZE ? new Object[this.containerSize = element.length] : new Object[this.containerSize = DEFAULT_SIZE];
        for (int i = 0; i < element.length; i++) {
            fixedArray[i] = element[i];
        }
        this.size = element.length;
    }

    @Override
    public void clear() {
        for (int i = 0; i < fixedArray.length; i++) {
            fixedArray[i] = null;
        }
        fixedArray = new Object[containerSize];
        size = EMPTY_SIZE;
    }

    @Override
    public E peek() {
        return isEmpty() ? null : (E) fixedArray[size - 1];
    }

    @Override
    public Object[] toArray() {
        return fixedArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentPos = size - 1;

            @Override
            public boolean hasNext() {
                return currentPos > -1 && fixedArray[currentPos] != null;
            }

            @Override
            public E next() {
                return (E) fixedArray[currentPos--];
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
        E tempObj = (E) fixedArray[size - 1];
        fixedArray[--size] = null;
        return tempObj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomAbstractFixedArrayCollection<?> that = (CustomAbstractFixedArrayCollection<?>) o;

        return Arrays.equals(fixedArray, that.fixedArray);
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(fixedArray);
        return result;
    }
    @Override
    public String toString() {
        return "CustomAbstractFixedArrayCollection{" +
                "fixedArray=" + Arrays.toString(fixedArray) +
                "size=" + size +
                "containerSize=" + containerSize +
                '}';
    }

}
