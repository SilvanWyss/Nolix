package ch.nolix.system.webgui.mainvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;
import ch.nolix.systemapi.webgui.mainvalidator.ILayerValidator;

/**
 * @author Silvan Wyss
 * @version 2023-10-29
 */
public final class LayerValidator implements ILayerValidator {

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertBelongsToGui(final ILayer<?> layer) {
    if (!layer.belongsToGui()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(layer, IWebGui.class);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertDoesNotBelongToGui(final ILayer<?> layer) {
    if (layer.belongsToGui()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(layer, layer.getStoredParentGui());
    }
  }
}
