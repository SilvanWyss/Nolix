//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

//interface
public interface IRootControlOwner<RCO extends IRootControlOwner<RCO>> extends Clearable {

  //method declaration
  Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  //method declaration
  IContainer<IControl<?, ?>> getStoredControls();

  //method declaration
  IControl<?, ?> getStoredRootControl();

  //method declaration
  RCO setRootControl(IControl<?, ?> rootControl);
}
