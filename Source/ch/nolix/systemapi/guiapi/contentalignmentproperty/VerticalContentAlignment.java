package ch.nolix.systemapi.guiapi.contentalignmentproperty;

import ch.nolix.coreapi.document.node.INode;

public enum VerticalContentAlignment {
  TOP,
  CENTER,
  BOTTOM;

  public static VerticalContentAlignment fromSpecification(final INode<?> specification) {

    final var verticalContentAlignmentString = specification.getSingleChildNodeHeader();

    return valueOf(verticalContentAlignmentString);
  }
}
