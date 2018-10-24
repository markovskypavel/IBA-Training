package by.iba.markovsky.festivalorganisation.model.data.stack;

import by.iba.markovsky.festivalorganisation.model.data.collection.CustomCollection;
import by.iba.markovsky.festivalorganisation.exception.CustomContainerEmptyException;
import by.iba.markovsky.festivalorganisation.exception.CustomStackOverflowException;

public interface Stack<E> extends CustomCollection<E> {
    boolean push(E element) throws CustomStackOverflowException;
    E pop() throws CustomContainerEmptyException;
}

