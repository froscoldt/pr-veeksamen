package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetupTestUsers {

    static ArrayList arr = new ArrayList();

    public static void checkIfContains() {
        if (arr.isEmpty()) {
            arr.add("avis");
            arr.add("hertz");
            arr.add("europcar");
            arr.add("budget");
            arr.add("alamo");
        }

    }

    public static String testSwappiFutureCalls(Integer week, String addr) {
        checkIfContains();
        ForkJoinPool executor = new ForkJoinPool(25,
                ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                null, false);
        List<Future<String>> futureArrayList = new ArrayList();

        for (int i = 0; i < arr.size(); i++) {
            Callable<String> worker = new Data(week, arr.get(i).toString(), addr);
            futureArrayList.add(executor.submit(worker));

        }

        StringBuilder sb = new StringBuilder();
        futureArrayList.parallelStream().forEach(future -> {
            try {
                String getFutureStr = future.get(5, TimeUnit.SECONDS);
                System.out.println("future value: " + getFutureStr + "\n");

                sb.append(getFutureStr);
            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
                Logger.getLogger(SetupTestUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return sb.toString();
    }

}
