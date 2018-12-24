/*
 * file:	Calculator.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	700
 */

//package declaration
package ch.nolix.core.mathematics;

//own imports
import ch.nolix.core.container.FPNPair;
import ch.nolix.core.container.List;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.validator.Validator;

//class
/**
 * This class provides some mathematical functions.
 * 
 * Most functions are provided in 2 different ways.
 * -as normal functions that can be called with suitable arguments
 * -as object that can be passed down
 */
public final class Calculator {
	
	//constant
	/**
	 * Is the default max deviation, that is 10^-9.
	 */
	public static final double DEFAULT_MAX_DEVIATION = 0.000000001;
	
	//function
	/**
	 * This function returns the average of its input values.
	 * This function throws an Exception if the given values are null or empty.
	 */
	public static IElementTakerElementGetter<Iterable<Double>, Double> DOUBLE_AVERAGE
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		//Calculates the number and the sum of the given values.
		int valueCount = 0;
		double sum = 0.0;
		for (double d: values) {
			valueCount++;
			sum += d;
		}
		
		return (sum / valueCount);
	};
	
	//function
	/**
	 * This function returns the maximum of its input values.
	 * This function throws an Exception if the given values are null or empty.
	 */
	public static IElementTakerElementGetter<Iterable<Double>, Double> DOUBLE_MAX
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		double max = values.iterator().next();
		for (Double d: values) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	};
	
	//function
	/**
	 * This function returns the minimum of its input values.
	 */
	public static final IElementTakerElementGetter<Iterable<Double>, Double> DOUBLE_MIN
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		double min = values.iterator().next();
		for (double v: values) {
			if (v < min) {
				min = v;
			}
		}
		
		return min;
	};
	
	//function
	/**
	 * This function returns the range of its input values.
	 * The range of some values is the difference between the maximum and the minimum of the values.
	 */
	public static final IElementTakerElementGetter<Iterable<Double>, Double> DOUBLE_RANGE
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		double min = values.iterator().next();
		double max = values.iterator().next();
		for (double v: values) {
			
			if (v < min) {
				min = v;
			}
			
			if (v > max) {
				max = v;
			}
		}
		
		return (max - min);
	};
	
	//function
	/**
	 * This function returns the square of its input value.
	 */
	public static final IElementTakerElementGetter<Double, Double> DOUBLE_SQUARE
	= (value) -> {
		return (value * value);
	};
	
	//function
	/**
	 * This function returns the sum of its input values.
	 */
	public static final IElementTakerElementGetter<Iterable<Double>, Double> DOUBLE_SUM
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		
		double sum = 0.0;
		for (Double v: values) {
			sum += v;
		}
		
		return sum;
	};
	
	//function
	/**
	 * This function returns the variance of its input values.
	 */
	public static final IElementTakerElementGetter<Iterable<Double>, Double> DOUBLE_VARIANCE
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		//Calculates the number and the sum of the squared differences of the given values.
		final double average = DOUBLE_AVERAGE.getOutput(values);
		int valueCount = 0;
		double sumOfSquaredDifferences = 0.0;
		for (double v: values) {
			valueCount++;
			sumOfSquaredDifferences += Math.pow(v - average, 2);
		}
		
		return (sumOfSquaredDifferences / valueCount);
	};
	
	//function
	public static final IElementTakerElementGetter<Iterable<Double>, Double> DOUBLE_STANDARD_DEVIATION
	= (values) -> {
		return Math.sqrt(DOUBLE_VARIANCE.getOutput(values));
	};
	
	//function
	/**
	 * This function returns the average of its input values.
	 */
	public static final IElementTakerElementGetter<Iterable<Long>, Double> LONG_AVERAGE
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		int valueCount = 0;
		long sum = 0;
		for (Long v: values) {
			valueCount++;
			sum += v;
		}
		
		return ((double)sum / valueCount);
	};
	
	//function
	/**
	 * This function returns the maximum of its input values.
	 */
	public static final IElementTakerElementGetter<Iterable<Long>, Long> LONG_MAX
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		long max = values.iterator().next();
		
		for (Long v: values) {
			if (v > max) {
				max = v;
			}
		}
		
		return max;
	};
	
	//function
	/**
	 * This function returns the minimum of its input values.
	 */
	public static final IElementTakerElementGetter<Iterable<Long>, Long> LONG_MIN
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		//Calculates the minimum of the given values.
		long min = values.iterator().next();
		for (Long v: values) {
			if (v < min) {
				min = v;
			}
		}
		
		return min;
	};
	
	//function
	/**
	 * This function returns the range of its input values.
	 * The range of some values is the difference between the maximum and the minimum of the values.
	 */
	public static final IElementTakerElementGetter<Iterable<Long>, Double> LONG_RANGE
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		if (!values.iterator().hasNext()) {
			throw new RuntimeException("No values are given.");
		}
		
		//Calculates the minimum and the maximum of the given values.
		double min = values.iterator().next();
		double max = values.iterator().next();
		for (long v: values) {
			
			if (v < min) {
				min = v;
			}
			
			if (v > max) {
				max = v;
			}
		}
		
		return (max - min);
	};
	
	//function
	/**
	 * This function returns the sum of its input values.
	 */
	public static final IElementTakerElementGetter<Iterable<Long>, Long> LONG_SUM
	= (values) -> {
	
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		
		long sum = 0;
		for (Long v: values) {
			sum += v;
		}
		
		return sum;
	};
	
	//function
	/**
	 * This function returns the variance of its input values.
	 */
	public static final IElementTakerElementGetter<Iterable<Long>, Double> LONG_VARIANCE
	= (values) -> {
		
		//Checks the given values.
		Validator.throwExceptionIfValueIsNull("values", values);
		
		//Calculates the variance of the given values.
		final double average = LONG_AVERAGE.getOutput(values);
		double variance = 0.0;
		for (long v: values) {
			variance += Math.pow(v - average, 2);
		}
		
		return variance;
	};
	
	//function
	public static final IElementTakerElementGetter<Iterable<Long>, Double> LONG_STANDARD_DEVIATION
	= (values) -> {
		return Math.sqrt(LONG_VARIANCE.getOutput(values));
	};
	
	//static method
	/**
	 * Creates list of FPN number from the given x-values and y-values.
	 * 
	 * @param xValues
	 * @param yValues
	 * @return a newly created list of FPN-pairs created from the given x-values and y-values
	 * @throws Exception if not as many x-values are given as y-values
	 */
	public static List<FPNPair> createFPNPairs(final double[] xValues, final double[] yValues) {
		
		Validator.throwExceptionIfValueIsNotEqual("number of y-values", xValues.length, yValues.length);
		
		final List<FPNPair> FPNPairs = new List<FPNPair>();
		
		for (int i = 0; i < xValues.length; i++) {
			FPNPairs.addAtEnd(new FPNPair(xValues[i], yValues[i]));
		}
		
		return FPNPairs;
	}
	
	//static method
	/**
	 * @param value1
	 * @param value2
	 * @return true if the given values equals approximately each other
	 */
	public static boolean equalsApproximatively(final double value1, final double value2) {
		return (Math.abs(value1 - value2) < DEFAULT_MAX_DEVIATION);
	}

	//static method
	/**
	 * @param value1
	 * @param value2
	 * @param maxDeviation
	 * @return true if the given values equals approximately each other with a deviation that is not bigger than the given max deviation
	 * @throws Exception if the given max deviation is negative
	 */
	public static boolean equalsApproximatively(
		final double value1,
		final double value2,
		final double maxDeviation) {
		
		//Checks the given max deviation.
		Validator.throwExceptionIfValueIsNegative("max deviation", maxDeviation);
		
		return (Math.abs(value1 - value2) < maxDeviation);
	}
		
	//static method
	/**
	 * @param values
	 * @return the average of the given values
	 * @throws Exception if the given values is empty.
	 */
	public static double getAverage(double... values) {
		
		Validator.throwExceptionIfValueIsNotPositive("number of values", values.length);
		
		return (getSum(values) / values.length);
	}
	
	//static method
	/**
	 * @param values
	 * @return the average of the given values
	 * @throws Exception if the given values is empty.
	 */
	public static int getAverage(int... values) {
		
		Validator.throwExceptionIfValueIsNotPositive("number of values", values.length);
		
		return (getSum(values) / values.length);
	}
	
	//static method
	/**
	 * @param values
	 * @return the average of the given values
	 * @throws Exception if the given values is empty.
	 */
	public static long getAverage(long... values) {
		
		Validator.throwExceptionIfValueIsNotPositive("number of values", values.length);
		
		return (getSum(values) / values.length);
	}
	
	//static method
	public static ARModel getARModell(final int pOrder, final double[] inputValues) {
		return new ARModel(pOrder, inputValues);
	}
	
	//static method
	/**
	 * @param degree
	 * @param xValues
	 * @param yValues
	 * @return a polynom that has the given degree and fits the given values
	 * @throws Exception if:
	 * -the given degree is negative
	 * -the given degree is bigger than the number of the given x-values
	 * -not as many x-values are given as y-values
	 */
	public static Polynom getFittingPolynom(int degree, double[] xValues, double[] yValues) {
		
		//Checks the given parameters.
		Validator.throwExceptionIfValueIsNegative("degree", degree);
		Validator.throwExceptionIfValueIsBigger("degree", xValues.length, degree);
		Validator.throwExceptionIfValueIsNotEqual("number of y-values", xValues.length, yValues.length);
		
		Matrix factor1 = new Matrix(xValues.length, degree + 1);
		double[] xMatrixValues = new double[factor1.getSize()];
		for (int i = 0; i < factor1.getRowCount(); i++) {
			for (int j = 0; j < factor1.getColumnCount(); j++) {
				xMatrixValues[i * factor1.getColumnCount() + j] = Math.pow(xValues[i], factor1.getColumnCount() - j - 1);
			}
		}
		factor1.setValues(xMatrixValues);

		Matrix solutionMatrix = new Matrix(yValues.length, 1);
		solutionMatrix.setValues(yValues);
		
		return factor1.getMinimalFactorMatrix(solutionMatrix).toPolynom();
	}
	
	//static method
	/**
	 * @param values
	 * @return the biggest value of the given values
	 */
	public static double getMax(double...values) {
		
		double max = values[0];
		
		for (double v: values) {
			if (v > max) {
				max = v;
			}
		}
		
		return max;
	}
	
	//static method
	/**
	 * @param values
	 * @return the biggest value of the given values
	 */
	public static int getMax(int...values) {
		
		int max = values[0];
		
		for (int v: values) {
			if (v > max) {
				max = v;
			}
		}
		
		return max;
	}
	
	//static method
	/**
	 * @param values
	 * @return the biggest value of the given values
	 */
	public static long getMax(long...values) {
		
		long max = values[0];
		
		for (long v: values) {
			if (v > max) {
				max = v;
			}
		}
		
		return max;
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values
	 */
	public static double getMin(double... values) {
			
		double min = values[0];
		
		for (double v: values) {
			if (v < min) {
				min = v;
			}
		}
		
		return min;
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values
	 */
	public static int getMin(int... values) {
		
		int min = values[0];
		
		for (int v: values) {
			if (v < min) {
				min = v;
			}
		}
		
		return min;
	}
	
	//static method
	/**
	 * @param values
	 * @return the smallest value of the given values
	 */
	public static long getMin(long... values) {
		
		long min = values[0];
		
		for (long v: values) {
			if (v < min) {
				min = v;
			}
		}
		
		return min;
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value
	 */
	public static double getSquare(final double value) {
		return (value * value);
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value
	 */
	public static double getSquare(final int value) {
		return (value * value);
	}
	
	//static method
	/**
	 * @param value
	 * @return the square of the given value
	 */
	public static double getSquare(final long value) {
		return (value * value);
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values
	 */
	public static double getSum(double... values) {
		
		double sum = 0;
		
		for (double v: values) {
			sum += v;
		}
		
		return sum;
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values
	 */
	public static int getSum(int... values) {
		
		int sum = 0;
		
		for (int v: values) {
			sum += v;
		}
		
		return sum;
	}
	
	//static method
	/**
	 * @param values
	 * @return the sum of the given values
	 */
	public static long getSum(long... values) {
		
		long sum = 0;
		
		for (long v: values) {
			sum += v;
		}
		
		return sum;
	}
	
	//static method
	/**
	 * @param value
	 * @return true if the given value is approximately 1.
	 */
	public static boolean isApproximatelyOne(final double value) {
		return (Math.abs(value - 1.0) < DEFAULT_MAX_DEVIATION);
	}
	
	//static method
	/**
	 * @param value
	 * @param maxDeviation
	 * @return true if the given value is approximately 1 with a deviation that is not bigger than the given max deviation
	 * @throws Exception if the given max deviation is negative
	 */
	public static boolean isApproximatelyOne(final double value, final double maxDeviation) {
		
		//Checks the given max deviation.
		Validator.throwExceptionIfValueIsNegative("max deviation", maxDeviation);
		
		return (Math.abs(value - 1.0) < maxDeviation);
	}
	
	//static method
	/**
	 * @param value
	 * @return true if the given value is approximately 0.
	 */
	public static boolean isApproximatelyZero(final double value) {
		return (Math.abs(value) < DEFAULT_MAX_DEVIATION);
	}
	
	//static method
	/**
	 * @param value
	 * @param maxDeviation
	 * @return true if the given value is approximately 0 with a deviation that is not bigger than the given max deviation
	 * @throws Exception if the given max deviation is negative
	 */
	public static boolean isApproximatelyZero(final double value, final double maxDeviation) {
		
		//Checks the given max deviation.
		Validator.throwExceptionIfValueIsNegative("max deviation", maxDeviation);
		
		return (Math.abs(value) < maxDeviation);
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be instantiated.
	 */
	private Calculator() {}
}
