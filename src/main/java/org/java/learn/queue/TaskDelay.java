package org.java.learn.queue;

import java.util.ArrayList;
import java.util.List;

public class TaskDelay {

    private volatile static List<ApplicationTask> tasks = new ArrayList<>();

    private static final TaskDelay instance = new TaskDelay();
    private TaskDelay(){}
    public static TaskDelay getInstance(){
        return instance;
    }

    public TaskDelay addTask(ApplicationTask task){
        tasks.add(task);
        return instance;
    }

    public void startTask(){
        for(int i=0; i<tasks.size(); i++){
            if(null == tasks.get(i)){
                continue;
            }
            tasks.get(i).execute();
        }
    }

    public static interface ApplicationTask {
        void execute();
    }
}
