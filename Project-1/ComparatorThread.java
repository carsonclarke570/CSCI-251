//
// File:	ComparatorThread.java
// Package:	---
// Unit:	Class ComparatorThread
//

/**
 * Class ComparatorThread provides an object that simulates a comparator using
 * values from a monitor. It is a subclass of class {@link java.lang.Thread
 * </CODE>Thread<CODE>}
 * 
 * 
 * @author Carson Clarke-Magrab
 * @version 8-February-2017
 */
public class ComparatorThread extends Thread {

	// Instance Variables
	private Monitor monitor;
	private int hiIn, loIn, hiOut, loOut;

	/**
	 * Constructs a new comparator thread
	 * 
	 * @param monitor The monitor storing the values
	 * @param hiIn Index of the first (top) input of the comparator
	 * @param loIn Index of the second (bottom) input of the comparator
	 * @param hiOut Index of the first (top) put of the comparator
	 * @param loOut Index of the second (bottom) output of the comparator
	 */
	public ComparatorThread(Monitor monitor, int hiIn, int loIn, int hiOut,
			int loOut) {
		this.monitor = monitor;
		this.hiIn = hiIn;
		this.loIn = loIn;
		this.hiOut = hiOut;
		this.loOut = loOut;
	}

	/**
	 * Runs the thread which simulates a comparator
	 */
	public void run() {
		try {
			monitor.putValue(hiOut,
					Math.min(monitor.getValue(hiIn), monitor.getValue(loIn)));
			monitor.putValue(loOut,
					Math.max(monitor.getValue(hiIn), monitor.getValue(loIn)));
		} catch (IllegalAccessException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
