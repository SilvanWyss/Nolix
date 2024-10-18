package ch.nolix.systemapi.webguiapi.controlstyleapi;

import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.fontapi.Font;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public interface IControlHeadStyle<CS extends IControlHeadStyle<CS>>
extends IMultiStateConfiguration<CS, ControlState> {

  <CS2 extends IControlHeadStyle<CS2>> void addChild(CS2 controlStyle);

  boolean getBoldTextFlagWhenHasState(ControlState state); //NOSONAR: This method returns a flag as a boolean.

  Font getFontWhenHasState(ControlState state);

  double getOpacityWhenHasState(ControlState state);

  IColor getTextColorWhenHasState(ControlState state);

  int getTextSizeWhenHasState(ControlState state);

  void removeCustomBoldTextFlags();

  void removeCustomFonts();

  void removeCustomOpacities();

  void removeCustomTextColors();

  void removeCustomTextSizes();

  CS setBoldTextFlagForState(ControlState state, boolean boldTextFlag);

  CS setFontForState(ControlState state, Font font);

  CS setOpacityForState(ControlState state, double opacity);

  CS setTextColorForState(ControlState state, IColor textColor);

  CS setTextSizeForState(ControlState state, int textSize);
}
