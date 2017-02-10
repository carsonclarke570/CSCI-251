//
// File:	OutputThread.java
// Package:	---
// Unit:	Class OutputThread
//

/**
 * Class OutputThread provides an object that prints the output of the sorting
 * network. It is a subclass of class {@link java.lang.Thread
 * </CODE>Thread<CODE>}
 * 
 * 
 * @author Carson Clarke-Magrab
 * @version 3-February-2017
 */
public class OutputThread extends Thread {

	// Instance Variables
	private Monitor monitor;

	/**
	 * Constructs a new output thread
	 * 
	 * @param monitor The monitor storing the output values
	 */
	public OutputThread(Monitor monitor) {
		this.monitor = monitor;
	}

	/**
	 * Gets the output values from the sorting network
	 */
	public void run() {
		try {
			System.out.print(monitor.getValue(62) + " ");
			System.out.print(monitor.getValue(63) + " ");
			System.out.print(monitor.getValue(61) + " ");
			System.out.print(monitor.getValue(57) + " ");
			System.out.print(monitor.getValue(51) + " ");
			System.out.print(monitor.getValue(43) + " ");
			System.out.print(monitor.getValue(33) + " ");
			System.out.print(monitor.getValue(21) + "\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
