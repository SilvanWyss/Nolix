//package declaration
package ch.nolix.system.webgui.controlhelper;

//own imports
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.structureapi.IAbsoluteOrRelativeInt;

//class
public final class ControlCssValueHelper {

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
