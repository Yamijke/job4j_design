package ru.job4j.list.dinamic_massive;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void increaseArray() {
        container = Arrays.copyOf(container, container.length * 2 + 1);
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            increaseArray();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        if (index < size) {
            T oldValue = container[index];
            container[index] = newValue;
            return oldValue;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index < size) {
            T oldElement = container[index];
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                    container.length - index - 1);
            container[container.length - 1] = null;
            size--;
            modCount++;
            return oldElement;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T get(int index) {
        if (index < size) {
            return container[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int currentPosition = 0;
            int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentPosition < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[currentPosition++];
            }
        };
    }
}

