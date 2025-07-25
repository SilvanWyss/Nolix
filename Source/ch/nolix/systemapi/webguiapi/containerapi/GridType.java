package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.coreapi.document.node.INode;

public enum GridType {
  INNER_LINES,
  INNER_AND_OUTER_LINES,
  NO_LINES;

  public static GridType fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
