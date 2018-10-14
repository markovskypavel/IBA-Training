package by.iba.markovsky.festivalorganisation.data.list;

import java.util.Arrays;
import java.util.Iterator;

public class CustomArrayList<E> extends CustomAbstractList<E> implements CustomList<E> {

    private Object[] arrayList;

    public CustomArrayList() {
        arrayList = new Object[EMPTY_SIZE];
        size = arrayList.length;
    }
    public CustomArrayList(E... element) {
        arrayList = new Object[element.length];
        for (int i = 0; i < element.length; i++) {
            arrayList[i] = element[i];
        }
        size = element.length;
    }
    public CustomArrayList(CustomArrayList<E> customArrayList) {
        this((E)customArrayList.arrayList);
    }
    public CustomArrayList(CustomList<E> customList) {
        this((E)customList.toArray());
    }

    @Override
    public boolean add(E element) {
        Object[] tempArray = arrayList;
        arrayList = new Object[++size];
        for (int i = 0; i < tempArray.length; i++) {
            arrayList[i] = tempArray[i];
        }
        arrayList[size - 1] = element;
        return true;
    }

    @Override
    public boolean addSeveral(E... element) {
        Object[] tempArray = arrayList;
        arrayList = new Object[size + element.length];
        for (int i = 0; i < tempArray.length; i++) {
            arrayList[i] = tempArray[i];
        }
        for (int i = 0; i < element.length; i++) {
            arrayList[tempArray.length + i] = element[i];
        }
        size += element.length;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < EMPTY_SIZE) return null;

        Object[] tempArray = arrayList;
        E tempObject = null;
        arrayList = new Object[--size];
        for (int i = 0, j = 0; i < tempArray.length; i++) {
            if (i != index) {
                arrayList[j++] = tempArray[i];
            } else {
                tempObject = (E) tempArray[i];
            }
        }
        return tempObject;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        } else{
            remove(index);
            return true;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arrayList[i] = null;
        }
        arrayList = new Object[EMPTY_SIZE];
        size = arrayList.length;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < EMPTY_SIZE) return null;

        E tempObject = null;
        for (int i = 0; i < arrayList.length; i++) {
            if (i == index) {
                tempObject = (E) arrayList[i];
                break;
            }
        }
        return tempObject;
    }

    @Override
    public E set(int index, E element) {
        if (index < EMPTY_SIZE || index >= size) return null;

        E tempObj = null;
        for (int i = 0; i < arrayList.length; i++) {
            if (i == index) {
                tempObj = (E) arrayList[i];
                arrayList[i] = element;
                break;
            }
        }
        return tempObj;
    }

    @Override
    public void add(int index, E element) {
        if(index < EMPTY_SIZE || index > size) index = size;

        Object[] tempArray = arrayList;
        arrayList = new Object[++size];
        for (int i = 0, j = 0; i < arrayList.length; i++) {
            if (i != index) {
                arrayList[i]=tempArray[j++];
            }else{
                arrayList[i] = element;
            }
        }
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < arrayList.length; i++) {
            if(o.equals(arrayList[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < arrayList.length; i++) {
            if(o.equals(arrayList[i])) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public String allIndexOf(Object o) {
        StringBuilder index = new StringBuilder();
        for (int i = 0; i < arrayList.length; i++) {
            if (o.equals(arrayList[i])) {
                index.append(i + " ");
            }
        }
        return index.toString().trim();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        return arrayList;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < arrayList.length && arrayList[currentIndex] != null;
            }

            @Override
            public E next() {
                return (E) arrayList[currentIndex++];
            }

            //Delete element on current index position
            @Override
            public void remove() {
                if (arrayList[currentIndex] == null) return;

                Object[] tempArray = arrayList;
                arrayList = new Object[--size];
                for (int i = 0, j = 0; i < tempArray.length; i++) {
                    if (i != currentIndex) {
                        arrayList[j++] = tempArray[i];
                    }
                }
            }

            public int getCurrentIndex(){
                return currentIndex;
            }
        };
    }

    @Override
    public E peek() {
        return isEmpty() ? null : (E) arrayList[size - 1];
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "arrayList=" + Arrays.toString(arrayList) +
                ",\nsize=" + size +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomArrayList<?> that = (CustomArrayList<?>) o;

        if (size != that.size) return false;
        return Arrays.equals(arrayList, that.arrayList);
    }
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(arrayList);
        result = 31 * result + size;
        return result;
    }

}
