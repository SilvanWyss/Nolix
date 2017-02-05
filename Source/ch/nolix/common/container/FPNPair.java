//package declaration
package ch.nolix.common.container;

//class
/**
 * A FPN pair (floating point number pair) contains two floating point numbers.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 100
 */
public final class FPNPair {

	//default value
	public static final double DEFAULT_VALUE = 0.0;
	
	//attributes
	private double value1 = DEFAULT_VALUE;
	private double value2 = DEFAULT_VALUE;
	
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
	 * @return true if this FPN pair equals the given object.
	 */
	public boolean equals(final Object object) {
				
		//Handles the case if the given object is no FPN pair.
		if (!(object instanceof FPNPair)) {
			return false;
		}
		
		//Handles the case if the given object is an FPN pair.
		final FPNPair fpnPair = (FPNPair)object;
		return (fpnPair.getValue1() == getValue1() && fpnPair.getValue2() == getValue2());
	}
	
	//method
	/**
	 * @return the value 1 of this FPN pair.
	 */
	public double getValue1() {
		return value1;
	}
	
	//method
	/**
	 * @return the value 2 of this FPN pair.
	 */
	public double getValue2() {
		return value2;
	}
	
	//method
	/**
	 * Sets the value 1 of this FPN pair.
	 * 
	 * @param value1
	 */
	public void setValue1(final double value1) {
		this.value1 = value1;
	}
	
	//method
	/**
	 * Sets the value 2 of this FPN pair.
	 * 
	 * @param value2
	 */
	public void setValue2(final double value2) {
		this.value2 = value2;
	}
	
	//method
	/**
	 * Sets the values of this FPN pair.
	 * 
	 * @param value1
	 * @param value2
	 */
	public void setValues(final double value1, final double value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
	
	//method
	/**
	 * @return a string representation of this FPN pair.
	 */
	public String toString() {
		return ("(" + getValue1() + "," + getValue2() + ")");
	}
}
