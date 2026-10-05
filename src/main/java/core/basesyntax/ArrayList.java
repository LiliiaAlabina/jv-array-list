package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayList = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            grow();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size == arrayList.length) {
                grow();
            }
            System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
            arrayList[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) arrayList[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            arrayList[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index >= 0 && index < size) {
            final T removedElement = (T) arrayList[index];
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(arrayList, index + 1, arrayList, index, numMoved);
            }
            size--;
            arrayList[size] = null;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for size " + size);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(arrayList[i], element)) {
                return remove(i);
            }
        }
        throw new java.util.NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = arrayList.length + arrayList.length / 2;
        arrayList = Arrays.copyOf(arrayList, newCapacity);
    }

    private boolean isEquals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
