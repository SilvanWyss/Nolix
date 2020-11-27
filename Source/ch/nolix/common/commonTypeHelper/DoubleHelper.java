//package declaration
package ch.nolix.common.commonTypeHelper;

//Java imports
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

//class
/**
 * This class provides methods to handle doubles.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 40
 */
public final class DoubleHelper {

	//static method
	/**
	 * The string representation of the given value has the following properties:
	 * -The separator symbol of the decimal places is a dot.
	 * -The decimal places and the separator symbol are only shown if there exist decimal places.
	 * 
	 * @param value
	 * @return a string representation of the given value.
	 */
	public static String toString(final double value) {
		
		final DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.US);
		decimalFormat.applyPattern("0.################");
		
		return decimalFormat.format(value);
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private DoubleHelper() {}
}
