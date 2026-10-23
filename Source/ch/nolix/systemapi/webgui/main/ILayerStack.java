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
  /**
   * @param control
   * @return true if the current {@link ILayerStack} contains the given control,
   *         false otherwise
   */
  boolean containsControl(Control<?, ?> control);

  /**
   * @return the number of {@link ILayer}s of the current {@link ILayerStack}
   */
  int getLayerCount();

  /**
   * @param internalId
   * @return a {@link Optional} with the {@link Control} with the given internalId
   *         of the current {@link ILayerStack} if the current {@link ILayerStack}
   *         contains such a {@link Control}, an empty {@link Optional} otherwise
   */
  Optional<Control<?, ?>> getOptionalStoredControlByInternalId(String internalId);

  /**
   * @return the {@link Control}s of the current {@link ILayerStack}
   */
  ExtendedIterable<Control<?, ?>> getStoredControls();

  /**
   * @return the {@link ILayer}s of the current {@link ILayerStack}
   */
  ExtendedIterable<ILayer> getStoredLayers();

  /**
   * @return the structure {@link Control}s of the current {@link ILayerStack}
   */
  ExtendedIterable<Control<?, ?>> getStoredStructureControls();

  /**
   * @return the top {@link ILayer} of the current {@link ILayerStack}
   * @throws RuntimeException if the current {@link ILayerStack} is empty
   */
  ILayer getStoredTopLayer();

  /**
   * @return true if the current {@link ILayerStack} has a remove layer action,
   *         false otherwise
   */
  boolean hasRemoveLayerAction();

  /**
   * Pushes the given layer on the current {@link ILayerStack}.
   * 
   * @param layer
   * @return the current {@link ILayerStack}
   */
  ILayerStack pushLayer(ILayer layer);

  /**
   * Pushes a new {@link ILayer} with the given rootControl on the current
   * {@link ILayerStack}.
   * 
   * @param rootControl
   * @return the current {@link ILayerStack}
   */
  ILayerStack pushLayerWithRootControl(Control<?, ?> rootControl);

  /**
   * Removes the given layer from the current {@link ILayerStack}.
   * 
   * @param layer
   */
  void removeLayer(ILayer layer);

  /**
   * Sets the given removeLayerAction to the current {@link ILayerStack}. The
   * given removeLayerAction is run when a {@link ILayer} is removed from the
   * current {@link ILayerStack}.
   * 
   * @param removeLayerAction
   * @return the current {@link ILayerStack}
   */
  ILayerStack setRemoveLayerAction(Runnable removeLayerAction);
}
