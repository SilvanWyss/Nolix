/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.errorcontrol.validator.Validator;

/**
 * @author Silvan Wyss
 */
public final class OpacityHelper {
  private OpacityHelper() {
  }

  public static double getOpacityFromString(final String string) {
    Validator.assertThat(string).thatIsNamed(String.class).isNotBlank();

    if (!string.endsWith("%")) {
      return Double.valueOf(string);
    }

    return (Double.valueOf(string.substring(0, string.length() - 1)) / 100);
  }
}
