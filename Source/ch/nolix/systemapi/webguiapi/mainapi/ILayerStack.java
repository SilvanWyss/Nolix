//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.generalstateapi.statemutationapi.Clearable;

//interface
public interface ILayerStack extends Clearable {

  //method declaration
  boolean containsControl(IControl<?, ?> control);

  //method declaration
  int getLayerCount();

  //method declaration
  Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  //method declaration
  IContainer<IControl<?, ?>> getStoredControls();

  //method declaration
  IContainer<ILayer<?>> getStoredLayers();

  //method declaration
  ILayer<?> getStoredTopLayer();

  //method declaration
  boolean hasRemoveLayerAction();

  //method declaration
  ILayerStack pushLayer(ILayer<?> layer);

  //method declaration
  ILayerStack pushLayerWithRootControl(IControl<?, ?> rootControl);

  //method declaration
  void removeLayer(ILayer<?> layer);

  //method declaration
  ILayerStack setRemoveLayerAction(Runnable removeLayerAction);
}
