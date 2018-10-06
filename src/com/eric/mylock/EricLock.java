package com.eric.mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class EricLock implements Lock {
	
	
	private Sync syncHandler = new Sync();
	
	/**
	 * 以内部类的方式继承AbstractQueuedSynchronizer
	 * @author Eric
	 */
	private class Sync extends AbstractQueuedSynchronizer {
		/**
		 * 序列号
		 */
		private static final long serialVersionUID = -2504783244310777966L;

		@Override
		protected boolean tryAcquire(int arg) {
			Thread currentThread = Thread.currentThread();
			int state = getState();
			if(state == 0){
				compareAndSetState(0, arg);
				setExclusiveOwnerThread(currentThread);
				return true;
			}else if(currentThread == getExclusiveOwnerThread()){
				setState(state + arg);
				return true;
			}
			return false;
		}
		
		@Override
		protected boolean tryRelease(int arg) {
			Thread currentThread = Thread.currentThread();
			if(currentThread != getExclusiveOwnerThread()){
				return false;
			}
			int state = getState() -arg;
			boolean free = false;
			if(state == 0){
				free = true;
				setExclusiveOwnerThread(null);
			}
			setState(state);
			return free;
		}
		/**
		 * 取condition
		 * @return
		 */
		protected Condition getCondition(){
			return new ConditionObject();
		}
	}
	
	@Override
	public void lock() {
		syncHandler.acquire(1);
	}
	
	@Override
	public void unlock() {
		syncHandler.release(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		syncHandler.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {	
		return syncHandler.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return syncHandler.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public Condition newCondition() {
		return syncHandler.getCondition();
	}

}
