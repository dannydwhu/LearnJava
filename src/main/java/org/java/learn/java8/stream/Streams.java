package org.java.learn.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    private enum Status{
        OPEN,CLOSED
    }
    
    static class Task{
        private Status status;
        private Integer point;
        
        Task(Status status, Integer point){
            this.setStatus(status);
            this.setPoint(point);
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Integer getPoint() {
            return point;
        }

        public void setPoint(Integer point) {
            this.point = point;
        }
    }
    
    public static void main(String[] args){
        List<Task> tasks = Arrays.asList(new Task(Status.OPEN,10), 
                new Task(Status.OPEN,2), new Task(Status.CLOSED,10));
        
        int sum = tasks.stream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(Task::getPoint).sum();
        System.out.println(sum);
        
        sum = tasks.stream().map(task->task.getPoint()).reduce(0,Integer::sum);
        System.out.println(sum);
        
        System.out.println(tasks.stream().collect(Collectors.groupingBy(Task::getStatus)));
        
        System.out.println(System.currentTimeMillis());
    }
    
}
