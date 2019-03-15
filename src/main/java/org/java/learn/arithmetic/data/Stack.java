package org.java.learn.arithmetic.data;

/**
 * @auther Danny Hu
 * @date 2019/3/12
 */
public class Stack {

    int[] array;
    int top; // 栈顶下标
    int maxSize; // 大小

    public Stack(int maxSize){
        this.maxSize = maxSize;
        array = new int[maxSize];
        top = -1;
    }

    public void push(int elem){
        array[++top] = elem;
    }

    public int peek(){
        return array[top] ;
    }

    public int pop(){
        return array[top--] ;
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public int size(){
        return top + 1;
    }

}
