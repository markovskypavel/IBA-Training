package by.iba.markovsky.festivalorganisation.model.data.queue;

import by.iba.markovsky.festivalorganisation.model.data.collection.CustomAbstractFixedArrayCollection;
import by.iba.markovsky.festivalorganisation.exception.CustomContainerEmptyException;
import by.iba.markovsky.festivalorganisation.exception.CustomQueueOverflowException;

import java.util.Arrays;

public class CustomArrayQueue<E> extends CustomAbstractFixedArrayCollection<E> implements Queue<E> {

    public CustomArrayQueue() {
        super();
    }
    public CustomArrayQueue(int size) {
        super(size);
    }
    public CustomArrayQueue(E... element) {
        super(element);
    }
    public CustomArrayQueue(CustomArrayQueue<E> arrQueue) {
        this((E) arrQueue.fixedArray);
    }
    public CustomArrayQueue(Queue<E> queue) {
        this((E) queue.toArray());
    }

    @Override
    public boolean enqueue(E element) throws CustomQueueOverflowException {
        if (size >= containerSize) {
            throw new CustomQueueOverflowException();
        }
        for (int i = size; i > 0; i--) {
            Object temp = fixedArray[i];
            fixedArray[i] = fixedArray[i - 1];
            fixedArray[i - 1] = temp;
        }
        fixedArray[0] = element;
        ++size;
        return true;
    }

    @Override
    public E dequeue() throws CustomContainerEmptyException {
        return deleteElement();
    }

    @Override
    public String toString() {
        return "CustomArrayQueue{" +
                "fixedArray=" + Arrays.toString(fixedArray) +
                "size=" + size +
                "containerSize=" + containerSize +
                '}';
    }

}
