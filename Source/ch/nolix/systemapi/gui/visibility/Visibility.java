/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.visibility;

import ch.nolix.baseapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public enum Visibility {
  VISIBLE,
  INVISIBLE;

  public static Visibility fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
