package by.iba.markovsky.festivalorganisation.data.list;

import by.iba.markovsky.festivalorganisation.data.collection.CustomAbstractCollection;

public abstract class CustomAbstractList<E> extends CustomAbstractCollection<E> implements CustomList<E> {

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
