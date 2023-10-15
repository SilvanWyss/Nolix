//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum LayerRole {
  BACKGROUND_LAYER,
  MAIN_LAYER,
  DIALOG_LAYER;

  // static method
  public static LayerRole fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
