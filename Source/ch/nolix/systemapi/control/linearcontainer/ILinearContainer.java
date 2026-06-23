/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.linearcontainer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> is the type of a {@link ILinearContainer}.
 * @param <S> is the type of the {@link ILinearContainerStyle} of a
 *            {@link ILinearContainer}.
 */
public interface ILinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends ch.nolix.systemapi.control.container.IContainer<C, S> {
  C addControl(Control<?, ?> control);

  C addControls(Control<?, ?>... controls);

  C addControls(ExtendedIterable<? extends Control<?, ?>> controls);

  void removeControl(Control<?, ?> control);
}
