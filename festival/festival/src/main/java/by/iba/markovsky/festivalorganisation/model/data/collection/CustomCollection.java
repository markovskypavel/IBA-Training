package by.iba.markovsky.festivalorganisation.model.data.collection;

public interface CustomCollection<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    void clear();
    Object[] toArray();
    E peek();
    boolean isFull();
}


