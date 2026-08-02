/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <O> the type of a {@link IRootControlOwner}.
 */
public interface IRootControlOwner<O extends IRootControlOwner<O>> extends Clearable {
  Optional<Control<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  ExtendedIterable<Control<?, ?>> getStoredControls();

  Control<?, ?> getStoredRootControl();

  O setRootControl(Control<?, ?> rootControl);
}
