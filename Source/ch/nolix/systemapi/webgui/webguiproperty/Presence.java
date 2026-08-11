/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.webguiproperty;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum Presence {
  VISIBLE,
  INVISIBLE,
  COLLAPSED;

  public static Presence fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
