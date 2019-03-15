package org.java.learn.arithmetic.data;

import java.util.LinkedList;

/**
 * @auther Danny Hu
 * @date 2019/3/13
 */
public class LinkList {

    private Node first;

    LinkedList l;

    private class Node{
        public Node next;
        Object value;

        Node(Object value){
            this.value = value;
        }
    }

    public LinkList(){
        first = null;
    }

    public void add(Object elem){
        if(null == first){
            first = new Node(null);
        }
        Node tmp = new Node(elem);
        tmp.next = first.next;
        first.next = tmp;
    }

    public void displayAll(){
        Node cur = first.next;

        while(null != cur){
            System.out.println(cur.value +" -> ");
            cur = cur.next;
        }
    }

    public static void main(String[] args){
        LinkList linkList = new LinkList();
        linkList.add(1);
        linkList.add(2);
        linkList.add(3);

        linkList.displayAll();
    }


}
