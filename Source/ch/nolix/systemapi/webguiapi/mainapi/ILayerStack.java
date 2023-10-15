//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//interface
public interface ILayerStack extends Clearable {

  // method declaration
  IControl<?, ?> getStoredControlOrNullByInternalId(String internalId);

  // method declaration
  IContainer<IControl<?, ?>> getStoredControls();

  // method declaration
  IContainer<ILayer<?>> getStoredLayers();

  // method declaration
  ILayer<?> getStoredTopLayer();

  // method declaration
  boolean hasRemoveLayerAction();

  // method declaration
  ILayerStack pushLayer(ILayer<?> layer);

  // method declaration
  ILayerStack pushLayerWithRootControl(IControl<?, ?> rootControl);

  // method declaration
  void removeLayer(ILayer<?> layer);

  // method declaration
  ILayerStack setRemoveLayerAction(IAction removeLayerAction);
}
