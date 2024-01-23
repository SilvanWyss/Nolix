//package declaration
package ch.nolix.core.commontypetool;

//Java imports
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

//class
/**
 * The {@link GlobalDoubleTool} provides methods to handle doubles. Of the
 * {@link GlobalDoubleTool} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-08-01
 */
public final class GlobalDoubleTool {

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalDoubleTool} can be created.
   */
  private GlobalDoubleTool() {
  }

  //static method
  /**
   * @param value
   * @return a {@link String} representation of the given value. The
   *         {@link String} representation will have the following properties:
   *         -The separator symbol of the decimal places is a dot. -The decimal
   *         places and the separator symbol are shown only if there exist decimal
   *         places.
   */
  public static String toString(final double value) {

    final var decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
    decimalFormat.applyPattern("0.################");

    return decimalFormat.format(value);
  }
}
