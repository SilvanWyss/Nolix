/*
 * file:	Worker.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	50
 */

//package declaration
package ch.nolix.application.performanceDetector;

//own import
import ch.nolix.core.mathematics.Calculator;

//package-visible class
final class Worker extends Thread {

	//attributes
	private final int n = 100;
	private final double[] xValues = new double[n];
	private final double[] yValues = new double[n];
	private long polynomFitsCount = 0;
	
	//constructor
	/**
	 * Creates new worker.
	 */
	public Worker() {
		
		//Initializes the x-values and y-values.
		for (int i = 0; i < n; i++) {
			xValues[i] = i;
			yValues[i] = 5 * Math.pow(i, 3) +  7 * Math.pow(i, 2) + 9 * i + i % 11;
		}
	}
	
	//method
	/**
	 * Runs this worker.
	 */
	public final void run() {
		while (true) {
			Calculator.getFittingPolynom(3, xValues, yValues);
			polynomFitsCount++;
		}
	}
	
	//method
	/**
	 * @return the number of polynom fits this worker has calculated yet
	 */
	public final long getPolynomFitsCount() {
		return polynomFitsCount;
	}
}
