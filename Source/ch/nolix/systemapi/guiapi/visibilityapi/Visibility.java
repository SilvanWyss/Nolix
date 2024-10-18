package ch.nolix.systemapi.guiapi.visibilityapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

public enum Visibility {
  VISIBLE,
  INVISIBLE;

  public static Visibility fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
