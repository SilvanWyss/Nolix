/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.guicomponent;

/**
 * A {@link LayerComponent} can belong to a layer.
 * 
 * @author Silvan Wyss
 * @param <L> the type of the layer a {@link LayerComponent} can belong to.
 */
public interface LayerComponent<L> {
  /**
   * @return true if the current {@link LayerComponent} belongs to a layer, false
   *         otherwise
   */
  boolean belongsToLayer();

  /**
   * @return the parent layer of the current {@link LayerComponent}
   * @throws RuntimeException if the current {@link LayerComponent} does not
   *                          belong to a layer
   */
  L getStoredParentLayer();
}
