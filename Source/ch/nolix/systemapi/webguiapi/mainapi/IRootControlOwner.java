package ch.nolix.systemapi.webguiapi.mainapi;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IRootControlOwner<RCO extends IRootControlOwner<RCO>> extends Clearable {

  Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  IContainer<IControl<?, ?>> getStoredControls();

  IControl<?, ?> getStoredRootControl();

  RCO setRootControl(IControl<?, ?> rootControl);
}
