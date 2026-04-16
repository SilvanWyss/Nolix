/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.validationlabel;

import ch.nolix.systemapi.atomiccontrol.validationlabel.IValidationLabel;

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
