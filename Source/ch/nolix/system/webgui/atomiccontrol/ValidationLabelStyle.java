package ch.nolix.system.webgui.atomiccontrol;

import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabelStyle;

public final class ValidationLabelStyle
extends ControlStyle<IValidationLabelStyle>
implements IValidationLabelStyle {

  public ValidationLabelStyle() {
    initialize();
  }
}
