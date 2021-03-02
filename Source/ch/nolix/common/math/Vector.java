//package declaration
package ch.nolix.common.math;

//Java import
import java.util.Arrays;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * A {@link Vector} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-02-01
 * @lines 180
 */
public final class Vector {
	
	//static method
	/**
	 * @param values
	 * @return a new {@link Vector} with the given values.
	 */
	public static Vector fromArray(final double[] values) {
		return new Vector(Arrays.copyOf(values, values.length));
	}
	
	//static method
	/**
	 * @param values
	 * @return a new {@link Vector} with the given values.
	 */
	public static Vector withValues(final double... values) {
		return new Vector(Arrays.copyOf(values, values.length));
	}
	
	//attribute
	private final double[] values;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link Vector} with the given values.
	 * 
	 * @param values
	 */
	Vector(final double[] values) {
		this.values = values;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return (object instanceof Vector && equals((Vector)object));
	}
			
	//method
	/**
	 * @return the euclid norm of the current {@link Vector}.
	 */
	public double getEuclidNorm() {
		
		var sum = 0.0;
		for (final var v: values) {
			sum += Math.pow(v, 2);
		}
		
		return Math.sqrt(sum);
	}
	
	//method
	/**
	 * @return the Manhattan norm of the current {@link Vector}.
	 */
	public double getManhattanNorm() {
		
		var manhattanNorm = 0.0;
		
		for (final var v: values) {
			manhattanNorm += Math.abs(v);
		}
		
		return manhattanNorm;
	}
	
	//method
	/**
	 * @param factor
	 * @return a new {@link Vector} that is the product of the current {@link Vector} with the given factor.
	 */
	public Vector getProduct(final double factor) {
		
		final var size = getSize();
		final var productValues = new double[size];
		
		for (var i = 0; i < size; i++) {
			productValues[i] = factor * values[i];
		}
		
		return new Vector(productValues);
	}
	
	//method
	/**
	 * @return the size of the current {@link Vector}.
	 */
	public int getSize() {
		return values.length;
	}
	
	//method
	/**
	 * @param vector
	 * @return a new {@link Vector} that is the sum of the current {@link Vector} and the given vector.
	 */
	public Vector getSum(final Vector vector) {
		
		final var size = getSize();
		final var sumValues = new double[size];
		
		for (var i = 0; i < size; i++) {
			sumValues[i] = values[i] + vector.values[i];
		}
		
		return new Vector(sumValues);
	}
	
	//method
	/**
	 * @param index
	 * @return the value at the given index
	 * @throws ArgumentIsOutOfRangeException
	 * if the given index is not positive or the given index is bigger than the size of the current {@link Vector}.
	 */
	public double getValueAt(int index) {
		
		Validator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isBetween(1, getSize());
				
		return values[index - 1];
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * @return an array with the values of the current {@link Vector}
	 */
	public double[] toArray() {
		return Arrays.copyOf(values, values.length);
	}
	
	//method
	/**
	 * @param vector
	 * @return true if the current {@link Vector} equals the given vector.
	 */
	private boolean equals(final Vector vector) {
		
		if (vector == null) {
			return false;
		}
		
		if (getSize() != vector.getSize()) {
			return false;
		}
		
		final var size = getSize();
		for (var i = 0; i < size; i++) {
			if (values[i] != vector.values[i]) {
				return false;
			}
		}
		
		return true;
	}
}
