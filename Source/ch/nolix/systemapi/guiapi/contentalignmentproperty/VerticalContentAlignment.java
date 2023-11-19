//package declaration
package ch.nolix.systemapi.guiapi.contentalignmentproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum VerticalContentAlignment {
  TOP,
  CENTER,
  BOTTOM;

  //static method
  public static VerticalContentAlignment fromSpecification(final INode<?> specification) {

    final var verticalContentAlignmentString = specification.getSingleChildNodeHeader();

    return valueOf(verticalContentAlignmentString);
  }
}
