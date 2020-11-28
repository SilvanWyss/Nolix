/*
 * file:	Timer.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	10
 */

//package declaration
package ch.nolix.common.time;

//class
public final class Timer {

	//attributes
	private long runMiliseconds;
	private boolean running;
	private long lastStartTimeInMilliseconds;
	
	//method
	/**
	 * @return the number of milliseconds this timer has run
	 */
	public int getRunMilliseconds() {
		
		int result = (int)runMiliseconds;
		
		if (isRunning()) {
			result += System.currentTimeMillis() - lastStartTimeInMilliseconds;
		}
		
		return result;
	}
	
	//method
	/**
	 * @return true if this timer is running
	 */
	public boolean isRunning() {
		return running;
	}
	
	//method
	/**
	 * Resets this timer.
	 */
	public synchronized void reset() {
		stop();
		runMiliseconds = 0;
	}
	
	//method
	/**
	 * Starts this timer if it is not already running.
	 */
	public synchronized void start() {
		if (!isRunning()) {
			lastStartTimeInMilliseconds = System.currentTimeMillis();
			running= true;
		}
	}
	
	//method
	/**
	 * Stops this timer if it is not already stopped.
	 */
	public synchronized void stop() {
		if (isRunning()) {
			runMiliseconds += System.currentTimeMillis() - lastStartTimeInMilliseconds;
			running = false;
		}
	}
}
