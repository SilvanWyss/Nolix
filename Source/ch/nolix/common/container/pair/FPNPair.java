//package declaration
package ch.nolix.common.container.pair;

//class
/**
 * A {@link FPNPair} contains two floating point numbers.
 * FPN = floating pointer number.
 * A {@link FPNPair} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 90
 */
public final class FPNPair {
	
	//constant
	public static final double DEFAULT_VALUE = 0.0;
	
	//attributes
	private final double value1;
	private final double value2;
	
	//constructor
	/**
	 * Creates a new {@link FPNPair} with default values.
	 */
	public FPNPair() {
		
		//Calls other constructor.
		this(DEFAULT_VALUE, DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new {@link FPNPair} with the given values.
	 * 
	 * @param value1
	 * @param value2
	 */
	public FPNPair(final double value1, final double value2) {
		
		//Sets the values of the current FPNPair.
		this.value1 = value1;
		this.value2 = value2;
	}
	
	//method
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public boolean equals(final Object object) {
				
		//Handles the case that the given object is not a FPNPair.
		if (!(object instanceof FPNPair)) {
			return false;
		}
		
		//Handles the case that the given object is a FPNPair.
		final var lFPNPair = (FPNPair)object;
		return (getValue1() == lFPNPair.getValue1() && getValue2() == lFPNPair.getValue2());
	}
	
	//method
	/**
	 * @return value 1 of the current {@link FPNPair}.
	 */
	public double getValue1() {
		return value1;
	}
	
	//method
	/**
	 * @return value 2 of the current {@link FPNPair}.
	 */
	public double getValue2() {
		return value2;
	}
	
	//method
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public String toString() {
		return ("(" + getValue1() + "," + getValue2() + ")");
	}
}
