package by.iba.markovsky.festivalorganisation.data.queue;

import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomContainerEmptyException;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomQueueOverflowException;
import by.iba.markovsky.festivalorganisation.view.View;

import java.util.Arrays;
import java.util.Objects;

public class TimeoutQueue<E> extends CustomArrayQueue<E> implements Queue<E> {

    private static final int TIMEOUT = 0;

    private class Node implements Runnable {

        private E element;
        private long millis;

        public Node(E element, long millis) {
            this.element = element;
            this.millis = millis;
        }

        public E getElement() {
            return element;
        }
        public long getMillis() {
            return millis;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(millis);
                millis = TIMEOUT;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return millis == node.millis &&
                    Objects.equals(element, node.element);
        }
        @Override
        public int hashCode() {
            return Objects.hash(element, millis);
        }
        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", millis=" + millis +
                    '}';
        }

    }

    public TimeoutQueue() {
        super();
    }
    public TimeoutQueue(int size) {
        super(size);
    }
    public TimeoutQueue(E... element) {
        super(element);
    }
    public TimeoutQueue(CustomArrayQueue<E> arrQueue) {
        this((E) arrQueue.toArray());
    }
    public TimeoutQueue(Queue<E> queue) {
        this((E) queue.toArray());
    }

    public void offer(E element, long millis) throws CustomQueueOverflowException {
        if (size >= containerSize) {
            throw new CustomQueueOverflowException();
        }
        Node newObject = new Node(element, millis);
        for (int i = size; i > 0; i--) {
            Object temp = fixedArray[i];
            fixedArray[i] = fixedArray[i - 1];
            fixedArray[i - 1] = temp;
        }
        fixedArray[0] = newObject;
        ++size;
        new Thread(newObject).start();
    }
    public E poll() throws CustomContainerEmptyException {
        if (isEmpty()) {
            throw new CustomContainerEmptyException();
        }
        while (!isEmpty()) {
            Node pollNode = (Node) deleteElement();
            if (pollNode.getMillis() > TIMEOUT) {
                return pollNode.getElement();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TimeoutQueue{" +
                "fixedArray=" + Arrays.toString(fixedArray) +
                "size=" + size +
                "containerSize=" + containerSize +
                '}';
    }

    public static void main(String[] args) {
        TimeoutQueue<Integer> timeoutQueue = new TimeoutQueue<>();
        try {
            timeoutQueue.offer(1, 1);
            timeoutQueue.offer(2, 1000);
            Thread.sleep(1);
            View.print(timeoutQueue.poll());
        } catch (CustomQueueOverflowException | CustomContainerEmptyException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
