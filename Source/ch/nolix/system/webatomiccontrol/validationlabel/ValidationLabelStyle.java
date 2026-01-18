/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.validationlabel;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabelStyle;

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
