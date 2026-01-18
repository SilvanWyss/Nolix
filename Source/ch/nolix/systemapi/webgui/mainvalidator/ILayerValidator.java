/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.mainvalidator;

import ch.nolix.systemapi.webgui.main.ILayer;

/**
 * @author Silvan Wyss
 */
public interface ILayerValidator {
  /**
   * @param layer
   * @throws RuntimeException if the given layer is null.
   * @throws RuntimeException if the given layer does not belong to a GUI.
   */
  void assertBelongsToGui(ILayer<?> layer);

  /**
   * @param layer
   * @throws RuntimeException if the given layer is null.
   * @throws RuntimeException if the given layer belongs to a GUI.
   */
  void assertDoesNotBelongToGui(ILayer<?> layer);
}
