package ch.nolix.systemapi.guiapi.contentalignmentproperty;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

public enum HorizontalContentAlignment {
  LEFT,
  CENTER,
  RIGHT;

  public static HorizontalContentAlignment fromSpecification(final INode<?> specification) {

    final var horizontalContentAlignmentString = specification.getSingleChildNodeHeader();

    return valueOf(horizontalContentAlignmentString);
  }
}
