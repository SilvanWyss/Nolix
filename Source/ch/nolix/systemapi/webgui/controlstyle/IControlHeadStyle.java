package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.element.multistateconfiguration.IMultiStateConfiguration;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.font.Font;
import ch.nolix.systemapi.gui.font.LineDecoration;
import ch.nolix.systemapi.webgui.main.ControlState;

public interface IControlHeadStyle<S extends IControlHeadStyle<S>>
extends IMultiStateConfiguration<S, ControlState> {
  <S2 extends IControlHeadStyle<S2>> void addChild(S2 controlStyle);

  boolean definesTextLineDecorationForState(ControlState state);

  boolean getBoldTextFlagWhenHasState(ControlState state); //NOSONAR: This method returns a flag as a boolean.

  Font getFontWhenHasState(ControlState state);

  double getOpacityWhenHasState(ControlState state);

  IColor getTextColorWhenHasState(ControlState state);

  LineDecoration getTextLineDecorationWhenHasState(ControlState state);

  int getTextSizeWhenHasState(ControlState state);

  void removeCustomBoldTextFlags();

  void removeCustomFonts();

  void removeCustomOpacities();

  void removeCustomTextColors();

  void removeCustomTextLineDecorations();

  void removeCustomTextSizes();

  S setBoldTextFlagForState(ControlState state, boolean boldTextFlag);

  S setFontForState(ControlState state, Font font);

  S setOpacityForState(ControlState state, double opacity);

  S setTextColorForState(ControlState state, IColor textColor);

  S setTextLineDecorationForState(ControlState state, LineDecoration textLineDecoration);

  S setTextSizeForState(ControlState state, int textSize);
}
