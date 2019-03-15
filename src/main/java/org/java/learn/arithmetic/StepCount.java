package org.java.learn.arithmetic;

public class StepCount {
    private static int count;
    static void f(int n){
        if(n<0){
            return;
        }
        if(n==0){
            count += 1;
        }
        f(n-1);
        f(n-2);
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args){
        StepCount stepCount = new StepCount();
        stepCount.f(10);
        System.out.println(stepCount.getCount());
    }
}
