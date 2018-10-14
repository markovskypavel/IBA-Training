package by.iba.markovsky.festivalorganisation.data.queue;

import by.iba.markovsky.festivalorganisation.data.collection.CustomCollection;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomContainerEmptyException;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomQueueOverflowException;

public interface Queue<E> extends CustomCollection<E> {
    boolean enqueue(E element) throws CustomQueueOverflowException;
    E dequeue() throws CustomContainerEmptyException;
}


