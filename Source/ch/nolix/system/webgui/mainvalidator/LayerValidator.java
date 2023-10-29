//package declaration
package ch.nolix.system.webgui.mainvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class LayerValidator {

  //method
  public void assertBelongsToGui(final ILayer<?> layer) {
    if (!layer.belongsToGui()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(layer, IWebGui.class);
    }
  }

  //method
  public void assertDoesNotBelongToGui(final ILayer<?> layer) {
    if (layer.belongsToGui()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(layer, layer.getStoredParentGui());
    }
  }
}
