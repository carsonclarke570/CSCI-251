//
// File:	SortNet.java
// Package:	---
// Unit:	Class SortNet
//

import java.util.LinkedList;

/**
 * A program designed to run eight integer values through a multi-threaded
 * sorting network.
 * <P>
 * Usage: java SortNet <I>x0</I> <I>x1</I> <I>x2</I> <I>x3</I> <I>x4</I>
 * <I>x5</I> <I>x6</I> <I>x7</I>
 * 
 * @author Carson Clarke-Magrab
 * @version 2-February-2017
 */
public class SortNet {

	/**
	 * Prevent construction
	 */
	private SortNet() {
	}

	/**
	 * Main program
	 */
	public static void main(String[] args) {
		//Error checking
		if (args.length != 8) {
			System.err.println("Wrong number of arguments.");
			usage();
			return;
		}
		
		int[] intArgs = new int[8];
		for (int i = 0; i < args.length; i++) {
			try {
				intArgs[i] = Integer.parseInt(args[i]);
			} catch (NumberFormatException e) {
				System.err.println("Argument " + args[i] + " was not formatted currectly");
				usage();
				return;
			}
		}
		
		Monitor monitor = new Monitor();
		
		OutputThread outThread = new OutputThread(monitor);
		outThread.start();
		
		LinkedList<Thread> comparators = new LinkedList<Thread>();
		comparators.add(new ComparatorThread(monitor, 0, 1, 2, 3));
		comparators.add(new ComparatorThread(monitor, 3, 4, 5, 6));
		comparators.add(new ComparatorThread(monitor, 6, 7, 8, 9));
		comparators.add(new ComparatorThread(monitor, 9, 10, 11, 12));
		comparators.add(new ComparatorThread(monitor, 12, 13, 14, 15));
		comparators.add(new ComparatorThread(monitor, 15, 16, 17, 18));
		comparators.add(new ComparatorThread(monitor, 18, 19, 20, 21));
		comparators.add(new ComparatorThread(monitor, 2, 5, 22, 23));
		comparators.add(new ComparatorThread(monitor, 23, 8, 24, 25));
		comparators.add(new ComparatorThread(monitor, 25, 11, 26, 27));
		comparators.add(new ComparatorThread(monitor, 27, 14, 28, 29));
		comparators.add(new ComparatorThread(monitor, 29, 17, 30, 31));
		comparators.add(new ComparatorThread(monitor, 31, 20, 32, 33));
		comparators.add(new ComparatorThread(monitor, 22, 24, 34, 35));
		comparators.add(new ComparatorThread(monitor, 35, 26, 36, 37));
		comparators.add(new ComparatorThread(monitor, 37, 28, 38, 39));
		comparators.add(new ComparatorThread(monitor, 39, 30, 40, 41));
		comparators.add(new ComparatorThread(monitor, 41, 32, 42, 43));
		comparators.add(new ComparatorThread(monitor, 34, 36, 44, 45));
		comparators.add(new ComparatorThread(monitor, 45, 38, 46, 47));
		comparators.add(new ComparatorThread(monitor, 47, 40, 48, 49));
		comparators.add(new ComparatorThread(monitor, 49, 42, 50, 51));
		comparators.add(new ComparatorThread(monitor, 44, 46, 52, 53));
		comparators.add(new ComparatorThread(monitor, 53, 48, 54, 55));
		comparators.add(new ComparatorThread(monitor, 55, 50, 56, 57));
		comparators.add(new ComparatorThread(monitor, 52, 54, 58, 59));
		comparators.add(new ComparatorThread(monitor, 59, 56, 60, 61));
		comparators.add(new ComparatorThread(monitor, 58, 60, 62, 63));

		for (Thread t : comparators) {
			t.start();
		}
		

		CommandLineThread commandThread = new CommandLineThread(monitor, intArgs);
		commandThread.start();
		
	}
	
	/**
	 * Prints a usage message
	 */
	private static void usage() {
		System.err.println("Usage: java SortNet <x0> <x1> <x2> <x3> <x4> <x5> <x6> <x7>");
	}
}
