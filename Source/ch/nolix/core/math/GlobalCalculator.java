//package declaration
package ch.nolix.core.math;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.pair.FPNPair;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.algebra.Matrix;
import ch.nolix.core.math.algebra.Polynom;
import ch.nolix.core.math.base.GlobalBaseCalculator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * The {@link GlobalCalculator} provides mathematical functions.
 * 
 * @author Silvan Wyss
 * @date 2016-05-01
 */
public final class GlobalCalculator {
	
	//constant
	/**
	 * The default maximum deviation is 10^-9.
	 */
	public static final double DEFAULT_MAX_DEVIATION = 0.000000001;
	
	//static method
	/**
	 * @param xValues
	 * @param yValues
	 * @return a new {@link LinkedList} with {@link FPNPair}s created from the given xValues and yValues.
	 * @throws InvalidArgumentException if the count of the given yValues does not equal the count of the given xValues.
	 */
	public static LinkedList<FPNPair> createFPNPairs(final double[] xValues, final double[] yValues) {
		return GlobalBaseCalculator.createFPNPairs(xValues, yValues);
	}
	
	//static method
	/**
	 * @param value1
	 * @param value2
	 * @return true if the given values equals approximately each other
	 * with a deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
	 */
	public static boolean equalsApproximatively(final double value1, final double value2) {
		return GlobalBaseCalculator.equalsApproximatively(value1, value2);
	}

	//static method
	/**
	 * @param value1
	 * @param value2
	 * @param maxDeviation
	 * @return true if the given values equals approximately each other
	 * with a deviation that is not bigger than the given maxDeviation.
	 * @throws NegativeArgumentException if the given maxDeviation is negative.
	 */
	public static boolean equalsApproximatively(final double value1, final double value2, final double maxDeviation) {
		return GlobalBaseCalculator.equalsApproximatively(value1, value2, maxDeviation);
	}
	
	//static method
	/**
	 * @param values
	 * @return the average of the given values.
	 * @throws EmptyArgumentException if the given values is empty.
	 */
	public static double getAverage(final double... values) {
		return GlobalBaseCalculator.getAverage(values);
	}
	
	//method
	/**
	 * @param values
	 * @return the average of the given values.
	 * @throws EmptyArgumentException if the given values is empty.
	 */
	public static double getAverage(final Iterable<Double> values) {
		return GlobalBaseCalculator.getAverage(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the average of the given values.
	 * @throws EmptyArgumentException if the given values is empty.
	 */
	public static int getAverage(final int... values) {
		return GlobalBaseCalculator.getAverage(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the average of the given values.
	 * @throws EmptyArgumentException if the given values is empty.
	 */
	public static long getAverage(final long... values) {
		return GlobalBaseCalculator.getAverage(values);
	}
	
	//static method
	/**
	 * @param pOrder
	 * @param inputValues
	 * @return a new {@link ARModel} with the given pOrder and inputValues.
	 * @throws NegativeArgumentException if the given pOrder is negative.
	 */
	public static ARModel getARModell(final int pOrder, final double[] inputValues) {
		return new ARModel(pOrder, inputValues);
	}
	
	//static method
	/**
	 * @param degree
	 * @param xValues
	 * @param yValues
	 * @return a new {@link Polynom} that has the given degree and fits the given values
	 * @throws NegativeArgumentException if the given degree is negative.
	 * @throws BiggerArgumentException if the given degree is bigger than the count of the given xValues.
	 * @throws InvalidArgumentException
	 * if the count of the given yValues does not equal the count of the given xValues.
	 */
	public static Polynom getFittingPolynom(final int degree, final double[] xValues, final double[] yValues) {
		
		//Asserts that the given degree is not negative.
		GlobalValidator.assertThat(degree).thatIsNamed(LowerCaseCatalogue.DEGREE).isNotNegative();
		
		//Asserts that the given degree is not bigger than the count of the given xValues.
		GlobalValidator.assertThat(degree).thatIsNamed(LowerCaseCatalogue.DEGREE).isNotBiggerThan(xValues.length);
		
		//Asserts that the count of the given yValues equals the count of the given xValues.
		GlobalValidator.assertThat(yValues).thatIsNamed("y-values container").hasSameSizeAs(xValues);
		
		final var factorMatrix = new Matrix(xValues.length, degree + 1);
		final var xMatrixValues = new double[factorMatrix.getSize()];
		for (var i = 0; i < factorMatrix.getRowCount(); i++) {
			for (var j = 0; j < factorMatrix.getColumnCount(); j++) {
				xMatrixValues[i * factorMatrix.getColumnCount() + j] =
				Math.pow(xValues[i], factorMatrix.getColumnCount() - j - 1.0);
			}
		}
		factorMatrix.setValues(xMatrixValues);

		final var solutionMatrix = new Matrix(yValues.length, 1);
		solutionMatrix.setValues(yValues);
		
		return factorMatrix.getMinimalFactorMatrix(solutionMatrix).toPolynom();
	}
	
	//static method
	/**
	 * @param values
	 * @return the biggest value of the given values.
	 */
	public static double getMax(final double...values) {
		return GlobalBaseCalculator.getMax(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the biggest value of the given values.
	 */
	public static int getMax(final int...values) {
		return GlobalBaseCalculator.getMax(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the biggest value of the given values.
	 */
	public static long getMax(final long...values) {
		return GlobalBaseCalculator.getMax(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values.
	 */
	public static double getMin(final double... values) {
		return GlobalBaseCalculator.getMin(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values.
	 */
	public static int getMin(final int... values) {
		return GlobalBaseCalculator.getMin(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values.
	 */
	public static long getMin(final long... values) {
		return GlobalBaseCalculator.getMin(values);
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value.
	 */
	public static double getSquare(final double value) {
		return GlobalBaseCalculator.getSquare(value);
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value.
	 */
	public static double getSquare(final int value) {
		return GlobalBaseCalculator.getSquare(value);
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value.
	 */
	public static double getSquare(final long value) {
		return GlobalBaseCalculator.getSquare(value);
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values.
	 */
	public static double getSum(final double... values) {
		return GlobalBaseCalculator.getSum(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values.
	 */
	public static int getSum(final int... values) {
		return GlobalBaseCalculator.getSum(values);
	}
	
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values.
	 */
	public static double getSum(final Iterable<Double> values) {
		return GlobalBaseCalculator.getSum(values);
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values.
	 */
	public static long getSum(final long... values) {
		return GlobalBaseCalculator.getSum(values);
	}
	
	//static method
	/**
	 * @param value
	 * @return true if the given value is approximately 1.0
	 * with a deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
	 */
	public static boolean isApproximatelyOne(final double value) {
		return GlobalBaseCalculator.isApproximatelyOne(value);
	}
	
	//static method
	/**
	 * @param value
	 * @param maxDeviation
	 * @return true if the given value is approximately 1.0
	 * with a deviation that is not bigger than the given maxDeviation.
	 * @throws NegativeArgumentException if the given maxDeviation is negative.
	 */
	public static boolean isApproximatelyOne(final double value, final double maxDeviation) {
		return GlobalBaseCalculator.isApproximatelyOne(value, maxDeviation);
	}
	
	//static method
	/**
	 * @param value
	 * @return true if the given value is approximately 0.0
	 * with a deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
	 */
	public static boolean isApproximatelyZero(final double value) {
		return GlobalBaseCalculator.isApproximatelyZero(value);
	}
	
	//static method
	/**
	 * @param value
	 * @param maxDeviation
	 * @return true if the given value is approximately 0.0
	 * with a deviation that is not bigger than the given maxDeviation.
	 * @throws NegativeArgumentException if the given maxDeviation is negative.
	 */
	public static boolean isApproximatelyZero(final double value, final double maxDeviation) {
		return GlobalBaseCalculator.isApproximatelyZero(value, maxDeviation);
	}
	

	
	//constructor
	/**
	 * Prevents that an instance of the {@link GlobalCalculator} can be created.
	 */
	private GlobalCalculator() {}
}
