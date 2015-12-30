package cn.cttic.sysframe.common.ftp;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConcurrentCapacity
{
  private Semaphore sem = null;
  private int capacity = 0;
  private int seconds = 0;

  public ConcurrentCapacity(int capacity, int seconds)
  {
    this.sem = new Semaphore(capacity);
    this.capacity = capacity;
    this.seconds = seconds;
  }

  public boolean acquire()
    throws Exception
  {
    boolean rtn = this.sem.tryAcquire(this.seconds, TimeUnit.SECONDS);
    if (!(rtn)) {
      throw new Exception("semaphore acquire timeout " + this.seconds + " seconds,capacity limit:" + this.capacity);
    }
    return rtn;
  }

  public void release()
  {
    this.sem.release();
  }
}