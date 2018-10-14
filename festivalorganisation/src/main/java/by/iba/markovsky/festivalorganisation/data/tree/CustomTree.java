package by.iba.markovsky.festivalorganisation.data.tree;

import by.iba.markovsky.festivalorganisation.data.collection.CustomCollection;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomTreeEmptyException;

public interface CustomTree<E> extends CustomCollection<E> {
    void add(E element);
    boolean contains(E element);
    E[] toArray();
    void remove(E element) throws CustomTreeEmptyException;
    StringBuilder preOrder() throws CustomTreeEmptyException;
    StringBuilder postOrder() throws CustomTreeEmptyException;
    StringBuilder inOrder() throws CustomTreeEmptyException;
}
