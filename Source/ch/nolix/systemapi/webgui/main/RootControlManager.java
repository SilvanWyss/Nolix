/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <O> the type of a {@link RootControlManager}.
 */
public interface RootControlManager<O extends RootControlManager<O>> extends Clearable {
  /**
   * @param internalId
   * @return a {@link Optional} with the {@link Control} with the given internalId
   *         of the current {@link RootControlManager} if the current
   *         {@link RootControlManager} contains such a {@link Control}, an empty
   *         {@link Optional} otherwise
   */
  Optional<Control<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  /**
   * @return the {@link Control}s of the current {@link RootControlManager}
   */
  ExtendedIterable<Control<?, ?>> getStoredControls();

  /**
   * @return the root {@link Control} of the current {@link RootControlManager}
   * @throws RuntimeException if the current {@link RootControlManager} does not
   *                          have a root {@link Control}
   */
  Control<?, ?> getStoredRootControl();

  /**
   * Sets the given rootControl to the current {@link RootControlManager}.
   * 
   * @param rootControl
   * @return the current {@link RootControlManager}
   */
  O setRootControl(Control<?, ?> rootControl);
}
