package ch.nolix.system.webgui.controltool;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.systemapi.elementapi.relativevalueapi.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.fontapi.LineDecoration;

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

  public ICssProperty getCssPropertyFromLineDecoration(final LineDecoration lineDecoration) {
    return //
    switch (lineDecoration) {
      case UNDERLINE -> CssProperty.withNameAndValue("text-decoration-line", "underline");
      case MIDLINE -> CssProperty.withNameAndValue("text-decoration-line", "line-through");
      case OVERLINE -> CssProperty.withNameAndValue("text-decoration-line", "overline");
      default -> throw InvalidArgumentException.forArgument(lineDecoration);
    };
  }
}
