package org.java.learn.graph;

import java.util.ArrayList;
import java.util.List;

public class DeepFirstCycle {

    private int max_count = 100;
    private List<String> nodes = new ArrayList<>();

    private int[][] matrix = new int[max_count][max_count];

    public int addNode(String nodeName){
        if(!nodes.contains(nodeName)){
            nodes.add(nodeName);
            return nodes.size()-1;
        }
        return nodes.indexOf(nodeName);
    }

    public void addLine(String startNode, String endNode){
        int start = addNode(startNode);
        int end   = addNode(endNode);
        if(start >=0 && end >= 0){
            matrix[start][end] = 1;
        }
    }

    public List<String> find(){
        List<Integer> trace = new ArrayList<>();
        List<String> result = new ArrayList<>();
        if(matrix.length > 0){
            findCycle(0,trace, result);
        }
        if(result.size() == 0){
            System.out.println("No cycle");
        }
        return result;
    }

    private void findCycle(int idx, List<Integer> trace, List<String> result){
        int j;
        if((j=trace.indexOf(idx)) != -1){
            StringBuffer sb = new StringBuffer();
            String startNode = nodes.get(trace.get(j));
            while(j<trace.size()){
                sb.append(nodes.get(trace.get(j)) + "-");
                j++;
            }
            result.add("cycle: " + sb.toString() + startNode);
            return;
        }
        trace.add(idx);
        for(int i=0; i<nodes.size(); i++){
            if(matrix[idx][i] == 1){
                findCycle(i, trace, result);
            }
        }
        trace.remove(trace.size()-1);
    }

    public static void main(String[] args){
        DeepFirstCycle cycle = new DeepFirstCycle();
        cycle.addLine("A","B");
        cycle.addLine("A","C");
        cycle.addLine("B","D");
        cycle.addLine("C","D");
        cycle.addLine("D","A");

        List<String> result = cycle.find();
        for(String s : result){
            System.out.println(s);
        }
    }

}
