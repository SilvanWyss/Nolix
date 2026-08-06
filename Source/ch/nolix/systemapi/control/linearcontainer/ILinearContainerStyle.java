/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.linearcontainer;

import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link ILinearContainerStyle}.
 */
public interface ILinearContainerStyle<S extends ILinearContainerStyle<S>> extends IControlStyle<S> {
  int getChildControlMarginWhenHasState(ControlState state);

  void removeCustomChildControlMargins();

  S setChildControlMarginForState(ControlState state, int childControlMargin);
}
