//package decalration
package ch.nolix.systemapi.guiapi.structureproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum HorizontalContentAlignment {
  LEFT,
  CENTER,
  RIGHT;

  // static method
  public static HorizontalContentAlignment fromSpecification(final INode<?> specification) {

    final var horizontalContentAlignmentString = specification.getSingleChildNodeHeader();

    return valueOf(horizontalContentAlignmentString);
  }
}
