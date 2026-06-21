/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <O> is the type of a {@link IRootControlOwner}.
 */
public interface IRootControlOwner<O extends IRootControlOwner<O>> extends Clearable {
  Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  ExtendedIterable<IControl<?, ?>> getStoredControls();

  IControl<?, ?> getStoredRootControl();

  O setRootControl(IControl<?, ?> rootControl);
}
