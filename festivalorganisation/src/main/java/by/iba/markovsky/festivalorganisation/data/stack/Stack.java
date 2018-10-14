package by.iba.markovsky.festivalorganisation.data.stack;

import by.iba.markovsky.festivalorganisation.data.collection.CustomCollection;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomContainerEmptyException;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomStackOverflowException;

public interface Stack<E> extends CustomCollection<E> {
    boolean push(E element) throws CustomStackOverflowException;
    E pop() throws CustomContainerEmptyException;
}

