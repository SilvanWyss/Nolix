package ch.nolix.systemapi.graphicapi.imageapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

public enum ImageApplication {
  SCALE_TO_FRAME,
  REPEATE;

  public static ImageApplication fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
