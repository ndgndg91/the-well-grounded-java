package com.ndgndg91.chapter5.thread;

public class ExceptionInThread {
    public static void main(String[] args) {
        var badThread = new Thread(() -> {
            throw new UnsupportedOperationException();
        });

        badThread.setName("An Exceptional Thread");

        badThread.setUncaughtExceptionHandler((t, e) -> {
            System.err.printf("Thread %d '%s' has thrown exception " + "%s at line %d of %s\n",
                    t.threadId(), t.getName(), e.toString(),
                    e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getFileName());
        });

        badThread.start();
    }
}
