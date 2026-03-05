package org.example.custom_collection;

import java.util.*;


public class CustomArrayList<T> extends AbstractList<T> {
    private Object[] list;
    int capacity;
    private int size = 0;

    public CustomArrayList() {
        this.capacity = 10;
        this.list = new Object[capacity];
    }

    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        this.list = new Object[capacity];

    }

    @Override
    public boolean add(T element) {
        if (size >= capacity) {
            capacity = capacity * 2;
            list = Arrays.copyOf(list, capacity);
        }
        list[size] = element;
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, list[i])) {
                list[i] = null;
                moveArray(i);
                size--;
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T temp = (T) list[index];
        list[index] = null;
        moveArray(index);
        size--;
        return temp;
    }


    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) list[index];
    }

    private void moveArray(int index) {
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T element) {
        T temp;
        if (index < size) {
            temp = (T) list[index];
            list[index] = element;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds, index = " + index + ", size = " + size);
        }
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (list[i] != null) {
                sb.append(list[i]);
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
