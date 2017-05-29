/*
 * file:	Vector.java
 * author:	Silvan Wyss
 * month:	2016-01
 * lines:	220
 */

//package declaration
package ch.nolix.core.mathematics;

//own import
import ch.nolix.core.validator.Validator;

//class
public final class Vector {
	
	//default value
	public static final int DEFAULT_LENTH = 3;
	
	public static Vector createVector(final double[] values) {
		return new Vector(values.length).setValues(values);
	}

	//attribute
	private double[] values;
	
	//constructor
	/**
	 * Creates new zero vector with the default length.
	 */
	public Vector() {
		
		//Calls other constructor.
		this(DEFAULT_LENTH);
	}
	
	//constructor
	/**
	 * Creates new zero vector with the given length.
	 * 
	 * @param length
	 * @throws Exception if the given length is not positive.
	 */
	public Vector(int length) {
		
		Validator.throwExceptionIfValueIsNotPositive("length", length);
		
		values = new double[length];
	}
	
	//constructor
	/**
	 * Creates new vector with the given length and whose values are set to the given value.
	 * 
	 * @param length
	 * @param value
	 * @throws Exception if the given length is not positive
	 */
	public Vector(int length, double value) {
		
		//Calls other constructor.
		this(length);
		
		setValuesTo(value);
	}
		
	//method
	/**
	 * Adds the given vector to this vector.
	 * 
	 * @param vector
	 * @return this vector
	 * @throws Exception if the given vector has not the same size as this vector
	 */
	public Vector add(Vector vector) {
		
		Validator.throwExceptionIfValueIsNotEqual("vector size", getSize(), vector.getSize());
		
		for (int i = 0; i < values.length; i++) {
			values[i] += vector.values[i];
		}
		
		return this;
	}
	
	//method
	/**
	 * @param object
	 * @return true if this vector equals the given object
	 */
	public final boolean equals(Object object) {
		
		if (object == null) {
			return false;
		}
		
		if (!(object instanceof Vector)) {
			return false;
		}
		
		Vector vector = (Vector)object;
		
		if (vector.getSize() != getSize()) {
			return false;
		}
		
		for (int i = 0; i < values.length; i++) {
			if (vector.values[i] != values[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * @return a clone of this vector
	 */
	public final Vector getClone() {
		
		Vector vector = new Vector();
		vector.values = this.values.clone();
		
		return vector;
	}
	
	//method
	/**
	 * @return the euclid norm of this vector
	 */
	public final double getEuclidNorm() {
		
		double sum = 0;
		
		for (int i = 0; i < values.length; i++) {
			sum += Math.pow(values[i], 2);
		}
		
		return Math.sqrt(sum);
	}
	
	//method
	/**
	 * @return the manhattan norm of this vector
	 */
	public final double getManhattanNorm() {
		
		double manhattanNorm = 0;
		
		for (double v: values) {
			manhattanNorm += Math.abs(v);
		}
		
		return manhattanNorm;
	}
	
	//method
	/**
	 * @return the size of this vector
	 */
	public final int getSize() {
		return values.length;
	}
	
	//method
	/**
	 * @param index
	 * @return the value at the given index
	 * @throws Exception if the given index is not positive or the given index is bigger than the size of this vector
	 */
	public final double getValueAt(int index) {
		
		Validator.throwExceptionIfValueIsNotInRange("index", 1, getSize(), index);
		
		return values[index - 1];
	}
	
	//method
	/**
	 * Multiplies this vector with the given factor.
	 * 
	 * @param factor
	 * @return this vector
	 */
	public final Vector multiplyWith(double factor) {
		
		for (int i = 0; i < values.length; i++) {
			values[i] *= factor;
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the values of this vector.
	 * 
	 * @param values
	 * @return this vector
	 * @throws Exception if not as many values are given as the size of this vector
	 */
	public final Vector setValues(double... values) {
		
		Validator.throwExceptionIfValueIsNotEqual("number of values", getSize(), values.length);
		
		for (int i = 0; i < this.values.length; i++) {
			this.values[i] = values[i];
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the values of this vector to the given value.
	 * 
	 * @param value
	 */
	public final void setValuesTo(double value) {
		for (int i = 0; i < values.length; i++) {
			values[i] = value;
		}
	}
	
	//method
	/**
	 * @return an array with the values of this vector
	 */
	public final double[] toArray() {
		return values.clone();
	}
}
