package ch.nolix.systemapi.guiapi.presenceapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

public enum Presence {
  VISIBLE,
  INVISIBLE,
  COLLAPSED;

  public static Presence fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
