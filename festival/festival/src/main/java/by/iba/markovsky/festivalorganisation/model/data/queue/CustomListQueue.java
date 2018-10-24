package by.iba.markovsky.festivalorganisation.model.data.queue;

import by.iba.markovsky.festivalorganisation.model.data.collection.CustomAbstractFixedListCollection;
import by.iba.markovsky.festivalorganisation.exception.CustomContainerEmptyException;
import by.iba.markovsky.festivalorganisation.exception.CustomQueueOverflowException;


public class CustomListQueue<E> extends CustomAbstractFixedListCollection<E> implements Queue<E> {

    public CustomListQueue() {
        super();
    }
    public CustomListQueue(int size) {
        super(size);
    }
    public CustomListQueue(E... element) {
        super(element);
    }
    public CustomListQueue(CustomListQueue<E> arrQueue) {
        this((E)arrQueue.toArray());
    }
    public CustomListQueue(Queue<E> queue) {
        this((E)queue.toArray());
    }

    @Override
    public boolean enqueue(E element) throws CustomQueueOverflowException {
        if (size >= containerSize) {
            throw new CustomQueueOverflowException();
        }
        linkedList.add(0, element);
        ++size;
        return true;
    }

    @Override
    public E dequeue() throws CustomContainerEmptyException {
        return deleteElement();
    }

    @Override
    public String toString() {
        return "CustomListQueue{" +
                "linkedList=" + linkedList +
                ", size=" + size +
                ", containerSize=" + containerSize +
                '}';
    }

}
