//package declaration
package ch.nolix.system.webgui.controltool;

//own imports
import ch.nolix.systemapi.elementapi.relativevalueapi.IAbsoluteOrRelativeInt;
//own imports
import ch.nolix.systemapi.graphicapi.colorapi.IColor;

//class
public final class ControlCssValueTool {

  //method
  public String getCssValueFromColor(final IColor color) {
    return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
  }

  //method
  public String getCssValueFromRelativeOrAbsoluteInt(
    final IAbsoluteOrRelativeInt absoluteOrRelativeInt,
    final String relativeIntCssUnit) {

    if (absoluteOrRelativeInt.isAbsolute()) {
      return absoluteOrRelativeInt.getAbsoluteValue() + "px";
    }

    return (100 * absoluteOrRelativeInt.getPercentage()) + relativeIntCssUnit;
  }
}
