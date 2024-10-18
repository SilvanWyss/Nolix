package ch.nolix.systemapi.webguiapi.mainapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

public enum LayerRole {
  BACKGROUND_LAYER,
  MAIN_LAYER,
  DIALOG_LAYER;

  public static LayerRole fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
