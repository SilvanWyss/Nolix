/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.validationlabel;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.atomiccontrol.validationlabel.IValidationLabelStyle;

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
