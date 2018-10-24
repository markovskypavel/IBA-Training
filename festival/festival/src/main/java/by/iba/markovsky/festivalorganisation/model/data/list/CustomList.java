package by.iba.markovsky.festivalorganisation.model.data.list;

import by.iba.markovsky.festivalorganisation.model.data.collection.CustomCollection;

public interface CustomList<E> extends CustomCollection<E> {
    boolean add(E element);
    boolean addSeveral(E... element);
    E remove(int index);
    boolean remove(Object o);
    E get(int index);
    E set(int index, E element);
    void add(int index, E element);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    String allIndexOf(Object o);
    boolean contains(Object o);
}
