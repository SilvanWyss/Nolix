package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.state.statemutation.Clearable;

public interface IRootControlOwner<O extends IRootControlOwner<O>> extends Clearable {
  Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  IContainer<IControl<?, ?>> getStoredControls();

  IControl<?, ?> getStoredRootControl();

  O setRootControl(IControl<?, ?> rootControl);
}
