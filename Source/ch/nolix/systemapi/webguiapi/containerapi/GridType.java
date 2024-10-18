package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

public enum GridType {
  INNER_LINES,
  INNER_AND_OUTER_LINES,
  NO_LINES;

  public static GridType fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
