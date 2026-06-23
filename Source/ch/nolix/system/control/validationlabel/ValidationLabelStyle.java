/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.validationlabel;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.control.validationlabel.IValidationLabelStyle;

/**
 * @author Silvan Wyss
 */
public final class ValidationLabelStyle
extends AbstractControlStyle<IValidationLabelStyle>
implements IValidationLabelStyle {
  public ValidationLabelStyle() {
    initialize();
  }
}
