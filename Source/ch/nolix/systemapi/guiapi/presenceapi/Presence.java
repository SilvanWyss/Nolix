//package declaration
package ch.nolix.systemapi.guiapi.presenceapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum Presence {
  VISIBLE,
  INVISIBLE,
  COLLAPSED;

  //static method
  public static Presence fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
