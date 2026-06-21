/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 */
public interface ILayerStack extends Clearable {
  boolean containsControl(IControl<?, ?> control);

  int getLayerCount();

  Optional<IControl<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  ExtendedIterable<IControl<?, ?>> getStoredControls();

  ExtendedIterable<ILayer> getStoredLayers();

  ExtendedIterable<IControl<?, ?>> getStoredStructureControls();

  ILayer getStoredTopLayer();

  boolean hasRemoveLayerAction();

  ILayerStack pushLayer(ILayer layer);

  ILayerStack pushLayerWithRootControl(IControl<?, ?> rootControl);

  void removeLayer(ILayer layer);

  ILayerStack setRemoveLayerAction(Runnable removeLayerAction);
}
