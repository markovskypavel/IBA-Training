package by.iba.markovsky.festivalorganisation.model.data.collection;

/**
 Abstract class for fixed-size containers.
 */
public abstract class CustomAbstractFixedCollection<E> extends CustomAbstractCollection<E> implements CustomCollection<E> {

    protected static final int DEFAULT_SIZE = 10;

    protected int containerSize;

    @Override
    public boolean isFull() {
        return size == containerSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomAbstractFixedCollection<?> that = (CustomAbstractFixedCollection<?>) o;

        return containerSize == that.containerSize;
    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + containerSize;
        return result;
    }
    @Override
    public String toString() {
        return "CustomAbstractFixedCollection{" +
                "containerSize=" + containerSize +
                '}';
    }

}
