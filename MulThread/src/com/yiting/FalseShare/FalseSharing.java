package com.yiting.FalseShare;

/**
 * 做500万次减法比较
 * 实验结果：        线程个数：                     4              3                 
 *          行填充技术 ：     25152278887ns    24340483890
 *          无填充技术：      37532518754ns    31048455697
 * @author yiting
 *
 */


public final class FalseSharing 
implements Runnable 
{ 
public final static int NUM_THREADS = 3; // change 
public final static long ITERATIONS = 500L * 1000L * 1000L; 
private final int arrayIndex; 

private static VolatileLong[] longs = new VolatileLong[NUM_THREADS]; 
static 
{ 
    for (int i = 0; i < longs.length; i++) 
    { 
        longs[i] = new VolatileLong(); 
    } 
} 

public FalseSharing(final int arrayIndex) 
{ 
    this.arrayIndex = arrayIndex; 
} 

public static void main(final String[] args) throws Exception 
{ 
    final long start = System.nanoTime(); 
    runTest(); 
    System.out.println("duration = " + (System.nanoTime() - start)); 
} 

private static void runTest() throws InterruptedException 
{ 
    Thread[] threads = new Thread[NUM_THREADS]; 

    for (int i = 0; i < threads.length; i++) 
    { 
        threads[i] = new Thread(new FalseSharing(i)); 
    } 

    for (Thread t : threads) 
    { 
        t.start(); 
    } 

    for (Thread t : threads) 
    { 
        t.join(); 
    } 
} 

public void run() 
{ 
    long i = ITERATIONS + 1; 
    while (0 != --i) 
    { 
        longs[arrayIndex].value = i; 
    } 
} 

public final static class VolatileLong 
{ 
    public volatile long value = 0L; 
    public long p1, p2, p3, p4, p5, p6; // comment out 
} 
} 