package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

public enum TextMode {
  NORMAL,
  SECRET;

  public static TextMode fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
