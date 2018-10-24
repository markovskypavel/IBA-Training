package by.iba.markovsky.festivalorganisation.model.data.collection;

public abstract class CustomAbstractCollection<E> implements CustomCollection<E> {

    protected static final int EMPTY_SIZE = 0;

    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomAbstractCollection<?> that = (CustomAbstractCollection<?>) o;

        return size == that.size;
    }
    @Override
    public int hashCode() {
        return size;
    }
    @Override
    public String toString() {
        return "CustomAbstractCollection{" +
                "size=" + size +
                '}';
    }

}
