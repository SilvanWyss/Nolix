/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.commontypetool.doubletool;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import ch.nolix.coreapi.commontypetool.doubletool.IDoubleTool;

/**
 * @author Silvan Wyss
 */
public final class DoubleTool implements IDoubleTool {
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
