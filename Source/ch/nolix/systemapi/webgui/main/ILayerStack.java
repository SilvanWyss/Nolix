/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 */
public interface ILayerStack extends Clearable {
  boolean containsControl(Control<?, ?> control);

  int getLayerCount();

  Optional<Control<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  ExtendedIterable<Control<?, ?>> getStoredControls();

  ExtendedIterable<ILayer> getStoredLayers();

  ExtendedIterable<Control<?, ?>> getStoredStructureControls();

  ILayer getStoredTopLayer();

  boolean hasRemoveLayerAction();

  ILayerStack pushLayer(ILayer layer);

  ILayerStack pushLayerWithRootControl(Control<?, ?> rootControl);

  void removeLayer(ILayer layer);

  ILayerStack setRemoveLayerAction(Runnable removeLayerAction);
}
