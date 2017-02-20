//
// File:	Monitor.java
// Package:	---
// Unit:	Class Monitor
//

/**
 * A monitor class that stores the input, intermediate, and output values in the
 * sorting network.
 * 
 * @author Carson Clarke-Magrab
 * @version 2-February-2017
 */
public class Monitor {

	// Instance Variables
	private Integer[] values;

	/**
	 * Constructs a Monitor object. The values are initially null.
	 */
	public Monitor() {
		values = new Integer[64];
	}

	/**
	 * Puts the given value into the monitor at an index.
	 * 
	 * @param i The index to put the value at
	 * @param value The value to place
	 * @exception IllegalAccessException Thrown if user tries to place a value
	 *            where there is already a value
	 */
	public synchronized void putValue(int i, int value)
			throws IllegalAccessException {
		if (values[i] != null) {
			System.err.println("Attempted to put the value " + value
					+ " at non-empty index " + i);
			System.exit(1);
		}
		values[i] = value;
		notifyAll();
	}

	/**
	 * Returns the value stored in the monitor at an index. If there's nothing
	 * there, wait until something has been put
	 * 
	 * @param i The index to get the value at
	 * @return The value at index i
	 */
	public synchronized int getValue(int i) throws InterruptedException {
		while (values[i] == null) {
			wait();
		}
		Integer v = values[i];
		notifyAll();
		return v;
	}
}
