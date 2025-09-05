package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.state.statemutation.Clearable;

public interface ILayerStack extends Clearable {
  boolean containsControl(IControl<?, ?> control);

  int getLayerCount();

  Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  IContainer<IControl<?, ?>> getStoredControls();

  IContainer<ILayer<?>> getStoredLayers();

  ILayer<?> getStoredTopLayer();

  boolean hasRemoveLayerAction();

  ILayerStack pushLayer(ILayer<?> layer);

  ILayerStack pushLayerWithRootControl(IControl<?, ?> rootControl);

  void removeLayer(ILayer<?> layer);

  ILayerStack setRemoveLayerAction(Runnable removeLayerAction);
}
