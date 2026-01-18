/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public enum LayerRole {
  BACKGROUND_LAYER,
  MAIN_LAYER,
  DIALOG_LAYER;

  public static LayerRole fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
