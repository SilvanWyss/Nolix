/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.gui.box.ICornerShadow;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link ShadowStyle}.
 */
public interface ShadowStyle<S extends ShadowStyle<S>> {
  ExtendedIterable<? extends ICornerShadow> getCornerShadowsWhenHasState(ControlState state);

  void removeCustomCornerShadows();

  S forStateSetCornerShadow(ControlState state, ICornerShadow cornerShadow);

  S forStateSetCornerShadows(ControlState state, ExtendedIterable<? extends ICornerShadow> cornerShadows);

  S forStateSetCornerShadows(ControlState state, ICornerShadow... cornerShadows);
}
