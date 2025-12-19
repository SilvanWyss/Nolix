package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface IControlAnalyser {
  boolean firstControlContainsSecondControl(IControl<?, ?> firstControl, IControl<?, ?> secondControl);
}
