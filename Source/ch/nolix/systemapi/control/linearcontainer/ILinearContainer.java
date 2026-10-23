/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.linearcontainer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link ILinearContainer}.
 * @param <S> the type of the {@link ILinearContainerStyle} of a
 *            {@link ILinearContainer}.
 */
public interface ILinearContainer<C extends ILinearContainer<C, S>, S extends ILinearContainerStyle<S>>
extends ch.nolix.systemapi.control.container.Container<C, S> {
  /**
   * Adds the given control to the current {@link ILinearContainer}.
   * 
   * @param control
   * @return the current {@link ILinearContainer}
   */
  C addControl(Control<?, ?> control);

  /**
   * Adds the given controls to the current {@link ILinearContainer}.
   * 
   * @param controls
   * @return the current {@link ILinearContainer}
   */
  C addControls(Control<?, ?>... controls);

  /**
   * Adds the given controls to the current {@link ILinearContainer}.
   * 
   * @param controls
   * @return the current {@link ILinearContainer}
   */
  C addControls(ExtendedIterable<? extends Control<?, ?>> controls);

  /**
   * Removes the given control from the current {@link ILinearContainer}.
   * 
   * @param control
   */
  void removeControl(Control<?, ?> control);
}
