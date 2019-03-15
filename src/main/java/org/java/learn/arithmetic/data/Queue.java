package org.java.learn.arithmetic.data;

import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * @auther Danny Hu
 * @date 2019/3/12
 */
public class Queue {
    private int maxSize;
    private int length;
    private int begin;
    private int end;
    private int[] queue;

    public Queue(int maxSize){
        this.maxSize = maxSize;
        queue = new int[maxSize];
        begin = 0;
        end = -1;
        length = 0;
    }

    ArrayQueue l;

    public void add(int elem){
        if(isFull()){
            throw new RuntimeException("Full");
        }
        if(end == maxSize -1){
            end = -1;
        }
        queue[++end] = elem;
        length++;
    }

    public int take(){
        if(isEmpty()){
            throw new RuntimeException("Empty");
        }
        int elem = queue[begin++];
        if(begin == maxSize){
            begin = 0;
        }
        length--;
        return elem;
    }

    private boolean isFull(){
        return length == maxSize;
    }

    private boolean isEmpty(){
        return length == 0;
    }

    public static void main(String[] args){
        Queue queue = new Queue(2);
        queue.add(1);
        queue.add(2);
        System.out.println(queue.take());
        queue.add(3);
//        queue.add(4);

        System.out.println(queue.take());
        System.out.println(queue.take());

    }

}
