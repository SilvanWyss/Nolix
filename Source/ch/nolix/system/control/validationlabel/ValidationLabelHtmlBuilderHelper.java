/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.validationlabel;

import ch.nolix.systemapi.control.validationlabel.IValidationLabel;

/**
 * @author Silvan Wyss
 */
public final class ValidationLabelHtmlBuilderHelper {
  private ValidationLabelHtmlBuilderHelper() {
  }

  public static String getHtmlDivInnerTextForValidationLabel(final IValidationLabel validationLabel) {
    if (validationLabel.containsAny()) {
      return validationLabel.getError().getMessage() + "\u2800";
    }

    return "\u2800";
  }
}
