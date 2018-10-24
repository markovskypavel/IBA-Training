package by.iba.markovsky.festivalorganisation.model.data.queue;

import by.iba.markovsky.festivalorganisation.model.data.collection.CustomCollection;
import by.iba.markovsky.festivalorganisation.exception.CustomContainerEmptyException;
import by.iba.markovsky.festivalorganisation.exception.CustomQueueOverflowException;

public interface Queue<E> extends CustomCollection<E> {
    boolean enqueue(E element) throws CustomQueueOverflowException;
    E dequeue() throws CustomContainerEmptyException;
}


