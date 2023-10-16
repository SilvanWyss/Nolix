//package declaration
package ch.nolix.systemapi.guiapi.processproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum TextMode {
  NORMAL,
  SECRET;

  //static method
  public static TextMode fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
