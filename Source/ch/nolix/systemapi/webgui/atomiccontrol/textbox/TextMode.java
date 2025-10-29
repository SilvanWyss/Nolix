package ch.nolix.systemapi.webgui.atomiccontrol.textbox;

import ch.nolix.coreapi.document.node.INode;

public enum TextMode {
  NORMAL,
  SECRET;

  public static TextMode fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
