package by.iba.markovsky.festivalorganisation.data.stack;

import by.iba.markovsky.festivalorganisation.data.collection.CustomAbstractFixedListCollection;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomContainerEmptyException;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.CustomStackOverflowException;

public class CustomListStack<E> extends CustomAbstractFixedListCollection<E> implements Stack<E>  {

    public CustomListStack() {
        super();
    }
    public CustomListStack(int size) {
        super(size);
    }
    public CustomListStack(E... element) {
        super(element);
    }
    public CustomListStack(CustomListStack<E> listStack) {
        this((E)listStack.toArray());
    }
    public CustomListStack(Stack<E> stack) {
        this((E)stack.toArray());
    }

    @Override
    public boolean push(E element) throws CustomStackOverflowException {
        if (size >= containerSize) {
            throw new CustomStackOverflowException();
        }
        linkedList.add(element);
        ++size;
        return true;
    }

    @Override
    public E pop() throws CustomContainerEmptyException {
        return deleteElement();
    }

    @Override
    public String toString() {
        return "CustomListStack{" +
                "linkedList=" + linkedList +
                ", size=" + size +
                ", containerSize=" + containerSize +
                '}';
    }

}
