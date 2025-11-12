package ch.nolix.systemapi.webgui.controlstyle;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.gui.box.ICornerShadow;
import ch.nolix.systemapi.webgui.main.ControlState;

public interface IShadowStyle<S extends IShadowStyle<S>> {
  IContainer<? extends ICornerShadow> getCornerShadowsWhenHasState(ControlState state);

  void removeCustomCornerShadows();

  S forStateSetCornerShadow(ControlState state, ICornerShadow cornerShadow, ICornerShadow... cornerShadows);

  S forStateSetCornerShadows(ControlState state, IContainer<? extends ICornerShadow> cornerShadows);
}
