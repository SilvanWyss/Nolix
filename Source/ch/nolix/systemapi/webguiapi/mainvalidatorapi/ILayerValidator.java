package ch.nolix.systemapi.webguiapi.mainvalidatorapi;

import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
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
