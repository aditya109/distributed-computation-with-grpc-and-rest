package io.github.core;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class MultiThread {
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Set<Future<Integer>> set = new HashSet<>();

        String[] argv = {
                "hehhllo",
                "avea",
                "asasd",
                "Asddsadasdasd"
        };

        for (String word : argv) {
            Callable<Integer> callable = new WordLengthCallable(word);
            Future<Integer> future = pool.submit(callable);
            set.add(future);
        }

        int i = 0;
        for (Future<Integer> future : set) {
            System.out.println(argv[i++] + " :-> " + future.get());
        }
    }

    public void test2() {
        String[] argv = {
                "hehhllo",
                "avea",
                "asasd",
                "Asddsadasdasd"
        };

        for (String word : argv) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(word + " :-> " + word.length());
        }
    }
}

class WordLengthCallable implements Callable<Integer> {

    public String word;

    public WordLengthCallable(String word) {
        this.word = word;
    }

    @Override
    public Integer call() throws Exception {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return word.length();
    }
}