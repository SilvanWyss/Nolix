package ch.nolix.systemapi.gui.visibility;

import ch.nolix.coreapi.document.node.INode;

public enum Visibility {
  VISIBLE,
  INVISIBLE;

  public static Visibility fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
