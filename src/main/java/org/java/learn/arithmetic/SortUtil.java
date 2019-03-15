package org.java.learn.arithmetic;

import java.util.Arrays;

public class SortUtil {

    public static void bubbleSort(int[] array){
        int len = array.length;
        for(int i=0; i<len; i++){
            for(int j=i; j<len; j++){
                if(array[j] < array[i]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
    }
    
    public static int middleSearch(int[] a, int target){
        Arrays.sort(a);
        int first = 0;
        int end = a.length;
        
        for(int i=0; i<a.length; i++){
            int middle = (first+end)/2;
            if(target == a[middle]){
                return middle;
            }
            
            if(target>a[middle]){
                first = middle +1;
            } else if(target < a[middle]){
                end = middle -1;
            }
        }
        return 0;
    }
    
    public static void main(String[] args){
        int[] a = {3,2,1,5,4};
//        fastSort(a,0,a.length-1);
        bubbleSort(a);
        for(int i : a){
            System.out.print(i+"-");
        }

    }
    
    public static int middleSearch1(int[] a, int target){
        if(null == a || a.length == 0){
            return -1;
        }
        Arrays.sort(a);
        
        int first = 0,middle = 0, end = a.length;
        for (int i=0; i<a.length; i++){
            middle = (int)(first+end)/2;
            
            if(target == a[middle]){
                return middle;
            }
            
            if(a[middle] < target){
                first = middle +1;
            } else {
                end = middle -1;
            }
        }
        
        
        return -1;
    }
    
    
    public static void fastSort(int[] a, int lo, int hi){
        if(lo>hi){
            return;
        }
        
        int index = partition(a, lo,hi);
        fastSort(a,lo, index - 1);
        fastSort(a,index+1, hi);
    }

    private static int partition(int[] a, int lo, int hi) {
        int key = a[lo];
        
        while(lo<hi){
            while(a[hi]>=key && hi>lo){
               hi--; 
            }
            a[lo]=a[hi];
            while(a[lo]<=key && hi>lo){
                lo++;
            }
            a[hi]=a[lo];
        }
        a[hi]=key;
        return hi;
    }
}
