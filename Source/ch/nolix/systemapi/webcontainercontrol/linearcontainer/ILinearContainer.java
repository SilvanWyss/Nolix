/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webcontainercontrol.linearcontainer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 * @param <C> is the type of a {@link ILinearContainer}.
 * @param <S> is the type of the {@link ILinearContainerStyle} of a
 *            {@link ILinearContainer}.
 */
public interface ILinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends ch.nolix.systemapi.webcontainercontrol.container.IContainer<C, S> {
  C addControl(IControl<?, ?> control);

  C addControls(IControl<?, ?>... controls);

  C addControls(IContainer<? extends IControl<?, ?>> controls);

  void removeControl(IControl<?, ?> control);
}
