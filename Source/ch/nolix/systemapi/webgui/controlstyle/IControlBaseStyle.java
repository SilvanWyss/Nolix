package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.systemapi.element.multistateconfiguration.IMultiStateConfiguration;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.font.Font;
import ch.nolix.systemapi.gui.font.LineDecoration;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> is the type of a {@link IControlBaseStyle}.
 */
public interface IControlBaseStyle<S extends IControlBaseStyle<S>> extends IMultiStateConfiguration<S, ControlState> {
  <S2 extends IControlBaseStyle<S2>> void addChild(S2 controlStyle);

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

  S forStateSetBoldTextFlag(ControlState state, boolean boldTextFlag);

  S forStateSetFont(ControlState state, Font font);

  S forStateSetOpacity(ControlState state, double opacity);

  S forStateSetTextColor(ControlState state, IColor textColor);

  S forStateSetTextLineDecoration(ControlState state, LineDecoration textLineDecoration);

  S forStateSetTextSize(ControlState state, int textSize);
}
