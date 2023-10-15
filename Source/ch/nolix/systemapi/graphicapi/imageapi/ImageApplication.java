//package declaration
package ch.nolix.systemapi.graphicapi.imageapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum ImageApplication {
  SCALE_TO_FRAME,
  REPEATE;

  // static method
  public static ImageApplication fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
