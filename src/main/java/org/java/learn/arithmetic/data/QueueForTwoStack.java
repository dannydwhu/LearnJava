package org.java.learn.arithmetic.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Danny Hu
 * @date 2019/3/14
 */
public class QueueForTwoStack {
    Stack pushStack = new Stack(100);
    Stack pullStack = new Stack(100);

    public void add(int value){
        pushStack.push(value);
    }

    public int take(){
        if(pullStack.size() > 0){
            return pullStack.pop();
        } else {
            if(pushStack.size() ==0){
                throw new RuntimeException("empty");
            }
            while(pushStack.size() > 0){
                pullStack.push(pushStack.pop());
            }
            return take();
        }
    }

    public static void main(String[] args){
        QueueForTwoStack queue = new QueueForTwoStack();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        print(queue.take());
        print(queue.take());
        queue.add(4);
        queue.add(5);
        print(queue.take());
        print(queue.take());
        print(queue.take());
        print(queue.take());
    }

    public static void print(int value){
        System.out.println(value);
    }
}
