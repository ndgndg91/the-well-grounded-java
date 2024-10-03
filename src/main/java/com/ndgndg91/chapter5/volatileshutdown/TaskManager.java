package com.ndgndg91.chapter5.volatileshutdown;

public class TaskManager implements Runnable {
    // 하드웨어 액세스 모드이며 캐시 하드웨어를 무시하고 대신 메인 메로리에서 직접 읽거나 쓰도록 CPU 명령을 생성.
    private volatile boolean shutdown = false;

    public void shutdown() {
        shutdown = true;
    }


    @Override
    public void run() {
        while (!shutdown) {
            // do some work : 작업 단위를 처리한다고 가정.
        }
    }
}
