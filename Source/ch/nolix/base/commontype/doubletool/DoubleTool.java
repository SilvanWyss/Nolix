/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.doubletool;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import ch.nolix.baseapi.commontype.doubletool.IDoubleTool;

/**
 * @author Silvan Wyss
 */
public final class DoubleTool implements IDoubleTool {
  private static final DecimalFormat DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);

  static {
    DECIMAL_FORMAT.applyPattern("0.0###############");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString(final double value) {
    return DECIMAL_FORMAT.format(value);
  }
}
