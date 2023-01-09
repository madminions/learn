package com.prep.learn.java.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Thread " + Thread.currentThread().getName();
            }
        };
        try {
            List<Future<String>> res = service.invokeAll(Arrays.asList(task, task, task));
            for(Future<String> ret: res){
                System.out.println(ret.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

//        try {
//            service.awaitTermination(10000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        service.shutdown();
    }

    void runnableTask(){
//        service.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("Thread " + Thread.currentThread().getName());
//                    TimeUnit.MILLISECONDS.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
