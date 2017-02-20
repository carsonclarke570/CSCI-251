//
// File:	CommandLineThread.java
// Package:	---
// Unit:	Class CommandLineThread
//

/**
 * Class CommandLineThread provides an object that places eight input values
 * (from the command line) into the sorting network. It is a subclass of class
 * {@link java.lang.Thread </CODE>Thread<CODE>}
 * 
 * 
 * @author Carson Clarke-Magrab
 * @version 2-February-2017
 */
public class CommandLineThread extends Thread {

	// Instance Variables
	private Monitor monitor;
	private int[] args;

	/**
	 * Constructs a new command line argument thread using the command line
	 * arguments.
	 * 
	 * @param monitor The monitor for storing the input values.
	 * @param args The command line arguments
	 */
	public CommandLineThread(Monitor monitor, int[] args) {
		this.monitor = monitor;
		this.args = args;
	}

	/**
	 * Places the command line arguments into the monitor.
	 */
	public void run() {
		try {
			monitor.putValue(0, args[0]);
			monitor.putValue(1, args[1]);
			monitor.putValue(4, args[2]);
			monitor.putValue(7, args[3]);
			monitor.putValue(10, args[4]);
			monitor.putValue(13, args[5]);
			monitor.putValue(16, args[6]);
			monitor.putValue(19, args[7]);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
