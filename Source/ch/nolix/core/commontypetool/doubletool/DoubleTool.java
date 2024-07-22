//package declaration
package ch.nolix.core.commontypetool.doubletool;

//Java imports
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

//own imports
import ch.nolix.coreapi.commontypetoolapi.doubletoolapi.IDoubleTool;

//class
/**
 * @author Silvan Wyss
 * @version 2016-08-01
 */
public final class DoubleTool implements IDoubleTool {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString(final double value) {

    final var decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
    decimalFormat.applyPattern("0.################");

    return decimalFormat.format(value);
  }
}
