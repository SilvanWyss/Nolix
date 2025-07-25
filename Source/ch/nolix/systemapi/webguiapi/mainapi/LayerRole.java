package ch.nolix.systemapi.webguiapi.mainapi;

import ch.nolix.coreapi.document.node.INode;

public enum LayerRole {
  BACKGROUND_LAYER,
  MAIN_LAYER,
  DIALOG_LAYER;

  public static LayerRole fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
