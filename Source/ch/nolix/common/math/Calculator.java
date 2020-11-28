//package declaration
package ch.nolix.common.math;

import ch.nolix.common.constant.MultiVariableNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.pair.FPNPair;
import ch.nolix.common.validator.Validator;

//class
/**
 * The {@link Calculator} provides mathematical functions.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 450
 */
public final class Calculator {
	
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
	 * @throws InvalidArgumentException
	 * if the count of the given yValues does not equal the count of the given xValues.
	 */
	public static LinkedList<FPNPair> createFPNPairs(final double[] xValues, final double[] yValues) {
		
		//Asserts that the count of the given yValues equals the count of the given xValues.
		Validator.assertThat(yValues).thatIsNamed("y-values container").hasSameSizeAs(xValues);
		
		final var lFPNPairs = new LinkedList<FPNPair>();
		for (var i = 0; i < xValues.length; i++) {
			lFPNPairs.addAtEnd(new FPNPair(xValues[i], yValues[i]));
		}
		
		return lFPNPairs;
	}
	
	//static method
	/**
	 * @param value1
	 * @param value2
	 * @return true if the given values equals approximately each other
	 * with a deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
	 */
	public static boolean equalsApproximatively(final double value1, final double value2) {
		
		//For a better performance, this method does not use all comfortable methods.
		return (Math.abs(value1 - value2) < DEFAULT_MAX_DEVIATION);
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
		
		//Asserts that the given maxDeviation is not negative.
		Validator.assertThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();
		
		return (Math.abs(value1 - value2) <= maxDeviation);
	}
	
	//static method
	/**
	 * @param values
	 * @return the average of the given values.
	 * @throws EmptyArgumentsException if the given values is empty.
	 */
	public static double getAverage(final double... values) {
		
		//Asserts that the given values is not empty.
		Validator.assertThat(values).thatIsNamed(MultiVariableNameCatalogue.VALUES).isNotEmpty();
		
		return (getSum(values) / values.length);
	}
	
	//method
	/**
	 * @param values
	 * @return the average of the given values.
	 * @throws EmptyArgumentsException if the given values is empty.
	 */
	public static double getAverage(final Iterable<Double> values) {
		
		//Asserts that the given values is not empty.
		Validator.assertThat(values).thatIsNamed(MultiVariableNameCatalogue.VALUES).isNotEmpty();
		
		var count = 0;
		var sum = 0.0;
		for (final var v : values) {
			count++;
			sum += v;
		}
		
		return (sum / count);
	}
	
	//static method
	/**
	 * @param values
	 * @return the average of the given values.
	 * @throws EmptyArgumentsException if the given values is empty.
	 */
	public static int getAverage(final int... values) {
		
		//Asserts that the given values is not empty.
		Validator.assertThat(values).thatIsNamed(MultiVariableNameCatalogue.VALUES).isNotEmpty();
		
		return (getSum(values) / values.length);
	}
	
	//static method
	/**
	 * @param values
	 * @return the average of the given values.
	 * @throws EmptyArgumentsException if the given values is empty.
	 */
	public static long getAverage(final long... values) {
		
		//Asserts that the given values is not empty.
		Validator.assertThat(values).thatIsNamed(MultiVariableNameCatalogue.VALUES).isNotEmpty();
		
		return (getSum(values) / values.length);
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
		Validator.assertThat(degree).thatIsNamed(VariableNameCatalogue.DEGREE).isNotNegative();
		
		//Asserts that the given degree is not bigger than the count of the given xValues.
		Validator.assertThat(degree).thatIsNamed(VariableNameCatalogue.DEGREE).isNotBiggerThan(xValues.length);
		
		//Asserts that the count of the given yValues equals the count of the given xValues.
		Validator.assertThat(yValues).thatIsNamed("y-values container").hasSameSizeAs(xValues);
		
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
		
		var max = values[0];	
		for (final var v : values) {
			if (v > max) {
				max = v;
			}
		}
		
		return max;
	}
	
	//static method
	/**
	 * @param values
	 * @return the biggest value of the given values.
	 */
	public static int getMax(final int...values) {
		
		var max = values[0];	
		for (final var v : values) {
			if (v > max) {
				max = v;
			}
		}
		
		return max;
	}
	
	//static method
	/**
	 * @param values
	 * @return the biggest value of the given values.
	 */
	public static long getMax(final long...values) {
		
		var max = values[0];
		for (final var v : values) {
			if (v > max) {
				max = v;
			}
		}
		
		return max;
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values.
	 */
	public static double getMin(final double... values) {
			
		var min = values[0];
		for (final var v : values) {
			if (v < min) {
				min = v;
			}
		}
		
		return min;
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values.
	 */
	public static int getMin(final int... values) {
		
		var min = values[0];	
		for (final var v : values) {
			if (v < min) {
				min = v;
			}
		}
		
		return min;
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values.
	 */
	public static long getMin(final long... values) {
		
		var min = values[0];
		for (final var v : values) {
			if (v < min) {
				min = v;
			}
		}
		
		return min;
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value.
	 */
	public static double getSquare(final double value) {
		return (value * value);
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value.
	 */
	public static double getSquare(final int value) {
		return (value * value);
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value.
	 */
	public static double getSquare(final long value) {
		return (value * value);
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values.
	 */
	public static double getSum(final double... values) {
		
		var sum = 0.0;
		for (final var v : values) {
			sum += v;
		}
		
		return sum;
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values.
	 */
	public static int getSum(final int... values) {
		
		var sum = 0;
		for (final var v : values) {
			sum += v;
		}
		
		return sum;
	}
	
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values.
	 */
	public static double getSum(final Iterable<Double> values) {
		
		var sum = 0.0;
		for (final var v : values) {
			sum += v;
		}
		
		return sum;
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values.
	 */
	public static long getSum(final long... values) {
		
		var sum = 0L;
		for (final var v : values) {
			sum += v;
		}
		
		return sum;
	}
	
	//static method
	/**
	 * @param value
	 * @return true if the given value is approximately 1.0
	 * with a deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
	 */
	public static boolean isApproximatelyOne(final double value) {
		
		//For a better performance, this method does not use all comfortable methods.
		return (Math.abs(value - 1.0) <= DEFAULT_MAX_DEVIATION);
	}
	
	//static method
	/**
	 * @param value
	 * @param maxDeviation
	 * @return true if the given value is approximately 1.0
	 * with a deviation that is not bigger than the given maxDeviation.
	 * @throws Exception if the given maxDeviation is negative.
	 */
	public static boolean isApproximatelyOne(final double value, final double maxDeviation) {
		
		//Asserts that the given maxDeviation is not negative.
		Validator.assertThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();
		
		return (Math.abs(value - 1.0) <= maxDeviation);
	}
	
	//static method
	/**
	 * @param value
	 * @return true if the given value is approximately 0.0
	 * with a deviation that is not bigger than {@value #DEFAULT_MAX_DEVIATION}.
	 */
	public static boolean isApproximatelyZero(final double value) {
		
		//For a better performance, this method does not use all comfortable methods.
		return (Math.abs(value) <= DEFAULT_MAX_DEVIATION);
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
		
		//Asserts that the given maxDeviation is not negative.
		Validator.assertThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();
		
		return (Math.abs(value) <= maxDeviation);
	}
	
	//static method
	/**
	 * @param value
	 * @return a new {@link IntRoundingMediator} for the given value.
	 */
	public static IntRoundingMediator round(final int value) {
		return new IntRoundingMediator(value);
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of this class can be instantiated.
	 */
	private Calculator() {}
}
