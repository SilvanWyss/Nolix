package ch.nolix.systemapi.gui.contentalignmentproperty;

import ch.nolix.coreapi.document.node.INode;

public enum HorizontalContentAlignment {
  LEFT,
  CENTER,
  RIGHT;

  public static HorizontalContentAlignment fromSpecification(final INode<?> specification) {

    final var horizontalContentAlignmentString = specification.getSingleChildNodeHeader();

    return valueOf(horizontalContentAlignmentString);
  }
}
