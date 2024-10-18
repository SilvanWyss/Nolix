package ch.nolix.systemapi.webguiapi.controltoolapi;

import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IControlAnalyser {

  boolean firstControlContainsSecondControl(IControl<?, ?> firstControl, IControl<?, ?> secondControl);
}
