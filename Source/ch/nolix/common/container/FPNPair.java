/*
 * file:	FPNPair.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	80
 */

//package declaration
package ch.nolix.common.container;

//class
/**
 * A FPN pair (floating point number pair) contains two floating point numbers.
 */
public final class FPNPair {

	//attributes
	private double value1 = 0.0;
	private double value2 = 0.0;
	
	//constructor
	/**
	 * Creates new FPN pair with default values.
	 */
	public FPNPair() {}
	
	//constructor
	/**
	 * Creates new FPN pair with the given values.
	 * @param value1
	 * @param value2
	 */
	public FPNPair(final double value1, final double value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
	
	//method
	/**
	 * @return the value 1 of this FPN pair
	 */
	public final double getValue1() {
		return value1;
	}
	
	//method
	/**
	 * @return the value 2 of this FPN pair
	 */
	public final double getValue2() {
		return value2;
	}
	
	//method
	/**
	 * Sets the value 1 of this FPN pair.
	 * @param value1
	 */
	public final void setValue1(final double value1) {
		this.value1 = value1;
	}
	
	//method
	/**
	 * Sets the value 2 of this FPN pair.
	 * @param value2
	 */
	public final void setValue2(final double value2) {
		this.value2 = value2;
	}
	
	//method
	/**
	 * Sets the values of this FPN pair.
	 * @param value1
	 * @param value2
	 */
	public final void setValues(final double value1, final double value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
}
