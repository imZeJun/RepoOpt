package com.demo.lizejun.repoopt;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo {

    private Lock lock = new ReentrantLock();
    private Condition mCondition1 = lock.newCondition();
    private Condition mCondition2 = lock.newCondition();
    private Condition mCondition3 = lock.newCondition();
    private Condition mCondition4 = lock.newCondition();

    public ThreadDemo() {
        WriteThread writeThread = new WriteThread(lock, 0, mCondition1, mCondition2);
        WriteThread writeThread2 = new WriteThread(lock, 1, mCondition2, mCondition3);
        WriteThread writeThread3 = new WriteThread(lock, 2, mCondition3, mCondition4);
        WriteThread writeThread4 = new WriteThread(lock, 3, mCondition4, mCondition1);
        writeThread.start();
        writeThread2.start();
        writeThread3.start();
        writeThread4.start();
    }

    public class WriteThread extends Thread {

        private int mNumber;
        private Condition mNextCondition;
        private Condition mCurCondition;
        private Lock mLock;

        WriteThread(Lock lock, int number, Condition curCondition, Condition nextCondition) {
            mLock = lock;
            mNumber = number;
            mNextCondition = nextCondition;
            mCurCondition = curCondition;
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                mLock.lock();
                try {
                    mCurCondition.await();
                    System.out.print("I am writing=" + mNumber);
                    mNextCondition.signal(); //通知阻塞在"空"条件上的线程.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mLock.unlock();
                }
            }
        }
    }
}
