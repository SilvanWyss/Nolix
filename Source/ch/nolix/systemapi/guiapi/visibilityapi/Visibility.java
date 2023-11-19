//package declaration
package ch.nolix.systemapi.guiapi.visibilityapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum Visibility {
  VISIBLE,
  INVISIBLE;

  //static method
  public static Visibility fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
