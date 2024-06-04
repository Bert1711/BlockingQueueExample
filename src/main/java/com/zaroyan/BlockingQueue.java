package com.zaroyan;
import java.util.LinkedList;

/**
 * @author Zaroyan
 */

public class BlockingQueue<T> {
    private LinkedList<T> queue;
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // Ждем, пока не освободится место в очереди
        }
        queue.add(item);
        notifyAll(); // Сигнализируем о том, что появился новый элемент
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Ждем, пока не появится новый элемент в очереди
        }
        T item = queue.remove();
        notifyAll(); // Сигнализируем о том, что элемент был извлечен
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}

