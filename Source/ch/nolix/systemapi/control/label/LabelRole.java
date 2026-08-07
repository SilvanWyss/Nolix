/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.label;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum LabelRole {
  TITLE,
  SUB_TITLE,
  LEVEL1_HEADER,
  LEVEL2_HEADER,
  LEVEL3_HEADER,
  LEVEL4_HEADER,
  LABEL,
  MAIN_LABEL,
  INFO_LABEL,
  WARNING_LABEL,
  ERROR_LABEL;

  public static LabelRole fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
