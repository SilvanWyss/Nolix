package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

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

  public static LabelRole fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
