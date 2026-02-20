/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.background;

import ch.nolix.baseapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public enum ImageApplication {
  SCALE_TO_FRAME,
  REPEAT;

  public static ImageApplication fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
