//package declaration
package ch.nolix.core.constants;

//class
/**
 * Of the {@link NumberCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-12
 * @lines 20
 */
public final class NumberCatalogue {
	
	//constants
	public static final double EULERS_NUMBER = Math.E;
	public static final double LOGARITHMUS_DECIMALIS_OF_2 = Math.log(2.0);
	public static final double PI = Math.PI;
	public static final double SQUARE_ROOT_OF_2 = Math.sqrt(2.0);
	public static final double SQUARE_ROOT_OF_3 = Math.sqrt(3.0);
	public static final double SQUARE_ROOT_OF_5 = Math.sqrt(5.0);
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link NumberCatalogue} can be created.
	 */
	private NumberCatalogue() {}
}
