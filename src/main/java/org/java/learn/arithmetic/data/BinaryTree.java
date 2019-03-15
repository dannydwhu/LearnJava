package org.java.learn.arithmetic.data;

import java.util.TreeSet;

/**
 * @auther Danny Hu
 * @date 2019/3/13
 */
public class BinaryTree {

    public Node root;

    public Node find(int value){
        if(root == null){
            return null;
        } else {
            Node cur = root;
            Node pre ;
            do{
                pre = cur;
                if(cur.value < value){
                    cur = cur.right;
                } else if(cur.value > value) {
                    cur = cur.left;
                } else {
                    return cur;
                }

            }while(pre.left !=null || pre.right!=null);
        }
        return null;
    }

    public void add(int value){
        if(null == root){
            root = new Node(value);
        } else {
            Node cur = root;
            while(true){
                if(cur.value > value){
                    if(cur.left == null){
                        cur.left = new Node(value);
                        return;
                    }
                    cur = cur.left;
                } else {
                    if(cur.right == null){
                        cur.right = new Node(value);
                        return;
                    }
                    cur = cur.right;
                }
            }
        }
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public int maxLeftLength;
        public int maxRightLength;

        public Node(){
        }

        public Node(int value){
            this.value = value;
        }

        public Node(Node left, int value, Node right){
            this.left = left;
            this.value = value;
            this.right = right;
        }
    }

    private int maxDistance;

    public int maxLeafDistance(){
        maxDistance = 0;
        getMaxDistance(root);
        return maxDistance;
    }
    private void getMaxDistance(Node node){
        if(null == node){
            return;
        }

        if(null == node.left){
            node.maxLeftLength = 0;
        }
        if(null !=node.left){
            getMaxDistance(node.left);
        }
        if(null == node.right){
            node.maxRightLength = 0;
        }
        if(null !=node.right){
            getMaxDistance(node.right);
        }

        if(null != node.left){
            int maxLeftLength = (node.left.maxLeftLength > node.left.maxRightLength) ? node.left.maxLeftLength + 1 : node.left.maxRightLength + 1;
            node.maxLeftLength = maxLeftLength;
        }
        if(null != node.right){
            int maxRightLength = (node.right.maxLeftLength > node.right.maxRightLength) ? node.right.maxLeftLength + 1 : node.right.maxRightLength + 1;
            node.maxRightLength = maxRightLength;
        }

        if(maxDistance < node.maxLeftLength + node.maxRightLength ){
            maxDistance = node.maxLeftLength + node.maxRightLength;
        }
    }

    public int treeWidth(Node node){
        int maxWidth = 0;
        int h = height(node);

        for(int i=1; i< h ; i++){
            int width = getWidth(node, i);
            if(width > maxWidth){
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    private int getWidth(Node node, int level) {
        if(null == node){
            return 0;
        }
        if(level == 1){
            return 1;
        } else if(level > 1) {
            return getWidth(node.left, level - 1) + getWidth(node.right, level - 1);
        }
        return 0;
    }

    int height(Node node){
        if(null == node){
            return 0;
        } else {
            int l = height(node.left);
            int r = height(node.right);
            return l > r ? l + 1 : r +1;
        }
    }


    public static void main(String[] args){
        BinaryTree tree = new BinaryTree();
        tree.add(3);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(7);
//        Node n = tree.find(4);
//        System.out.println(null != n ? n.toString() : -1);

        System.out.println(tree.maxLeafDistance());
    }

}
