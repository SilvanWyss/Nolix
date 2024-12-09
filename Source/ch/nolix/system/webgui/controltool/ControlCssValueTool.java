package ch.nolix.system.webgui.controltool;

import ch.nolix.systemapi.elementapi.relativevalueapi.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;

public final class ControlCssValueTool {

  public String getCssValueFromColor(final IColor color) {
    return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
  }

  public String getCssValueFromRelativeOrAbsoluteInt(
    final IAbsoluteOrRelativeInt absoluteOrRelativeInt,
    final String relativeIntCssUnit) {

    if (absoluteOrRelativeInt.isAbsolute()) {
      return absoluteOrRelativeInt.getAbsoluteValue() + "px";
    }

    return (100 * absoluteOrRelativeInt.getPercentage()) + relativeIntCssUnit;
  }
}
