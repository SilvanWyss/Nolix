//package declaration
package ch.nolix.core.tuple;

//class
/**
 * A {@link FPNPair} (floating point number pair) contains two floating point numbers.
 * A {@link FPNPair} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 70
 */
public final class FPNPair {
	
	//default value
	public static final double DEFAULT_VALUE = 0.0;
	
	//attributes
	private double value1 = DEFAULT_VALUE;
	private double value2 = DEFAULT_VALUE;
	
	//constructor
	/**
	 * Creates a new {@link FPNPair} with default values.
	 */
	public FPNPair() {}
	
	//constructor
	/**
	 * Creates a new {@link FPNPair} with the given values.
	 * 
	 * @param value1
	 * @param value2
	 */
	public FPNPair(final double value1, final double value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
	
	//method
	/**
	 * @return true if the current {@link FPNPair} equals the given object.
	 */
	@Override
	public boolean equals(final Object object) {
				
		//Handles the case that the given object is not a FPN pair.
		if (!(object instanceof FPNPair)) {
			return false;
		}
		
		//Handles the case that the given object is a FPN pair.
		final FPNPair fpnPair = (FPNPair)object;
		return (fpnPair.getValue1() == getValue1() && fpnPair.getValue2() == getValue2());
	}
	
	//method
	/**
	 * @return the value 1 of the current {@link FPNPair}.
	 */
	public double getValue1() {
		return value1;
	}
	
	//method
	/**
	 * @return the value 2 of the current {@link FPNPair}.
	 */
	public double getValue2() {
		return value2;
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * @return a string representation of the current {@link FPNPair}.
	 */
	@Override
	public String toString() {
		return ("(" + getValue1() + "," + getValue2() + ")");
	}
}
