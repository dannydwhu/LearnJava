package org.java.learn.queue;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskManager {
    private TaskManager() { }
    private static class LazyHolder {
        private static TaskManager taskQueueDaemonThread = new TaskManager();
    }
    public static TaskManager getInstance() {
        return LazyHolder.taskQueueDaemonThread;
    }

    Executor executor = Executors.newFixedThreadPool(20);



}
