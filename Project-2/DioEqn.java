
//
// File:	DioEqn.java
// Package:	---
// Unit:	Class DioEqn
//

import edu.rit.pj2.LongLoop;
import edu.rit.pj2.Task;
import edu.rit.pj2.vbl.LongVbl;

/**
 * 
 * The class DioEqn attempts to brute force the solution using parallel concepts
 * to the Diophantine equation x^2 + y^2 = C, where C is given through the
 * command line.
 * <P>
 * Usage: java pj2 DioEqn <I>C</I>
 * 
 * @author Carson Clarke-Magrab
 * @version 22-February-2017
 */
public class DioEqn extends Task {

	/**
	 * Main program
	 */
	public void main(String[] args) throws Exception {

		// Check for right number of arguments
		if (args.length != 1) {
			System.err.println("Wrong number of arguments.");
			usage();
		}

		// Parse the <C< argument. Must return because c is final
		final long c;
		try {
			c = Long.parseLong(args[0]);
		} catch (NumberFormatException e) {
			System.err.println(
					"Argument " + args[0] + " was not formatted currectly");
			usage();
			return;
		}

		// c must be greater than or equal to 0
		if (c < 0) {
			System.err.println("C cannot be negative");
			usage();
		}

		// Find lowest x and highest y fulfilling the Diophantine equation
		long sqrtC = (long) Math.sqrt(c);
		LongVbl lowX = new LongVbl.Min(Long.MAX_VALUE);
		LongVbl hiX = new LongVbl.Max(Long.MIN_VALUE);
		parallelFor(0, sqrtC).exec(new LongLoop() {
			LongVbl thrLowX, thrHiX;

			public void start() {
				thrLowX = threadLocal(lowX);
				thrHiX = threadLocal(hiX);
			}

			public void run(long i) throws Exception {
				long y = (long) Math.sqrt(c - (i * i));
				if (((i * i) + (y * y)) == c) {
					thrLowX.item = Math.min(thrLowX.item, i);
					if (y >= i) {
						thrHiX.item = Math.max(thrHiX.item, i);
					}
				}
			}
		});

		// Print result
		if (lowX.item == Long.MAX_VALUE) {
			System.out.println("No solutions");
		} else {
			printSolution(c, lowX.item);
		}

		if (hiX.item != Long.MIN_VALUE && hiX.item != lowX.item) {
			printSolution(c, hiX.item);
		}
	}

	/**
	 * Print a usage message and exit
	 */
	private static void usage() {
		System.err.println("Usage: java pj2 DioEqn <C>");
		terminate(1);
	}

	/**
	 * Prints a solution to the Diophantine equation
	 * 
	 * @param c The Diophantine constant
	 * @param x The x value of the solution
	 */
	private static void printSolution(long c, long x) {
		long y = (long) Math.sqrt(c - (x * x));
		System.out.println(x + "^2 + " + y + "^2 = " + c);
	}
}
