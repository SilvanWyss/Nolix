package ch.nolix.systemapi.gui.background;

import ch.nolix.coreapi.document.node.INode;

public enum ImageApplication {
  SCALE_TO_FRAME,
  REPEAT;

  public static ImageApplication fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
