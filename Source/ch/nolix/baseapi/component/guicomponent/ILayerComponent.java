/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.component.guicomponent;

/**
 * A {@link ILayerComponent} can belong to a layer.
 * 
 * @author Silvan Wyss
 * @param <L> is the type of the layer a {@link ILayerComponent} can belong to.
 */
public interface ILayerComponent<L> {
  /**
   * @return true if the current {@link ILayerComponent} belongs to a layer, false
   *         otherwise.
   */
  boolean belongsToLayer();

  /**
   * @return the parent layer of the current {@link ILayerComponent}.
   * @throws RuntimeException if the current {@link ILayerComponent} does not
   *                          belong to a layer.
   */
  L getStoredParentLayer();
}
