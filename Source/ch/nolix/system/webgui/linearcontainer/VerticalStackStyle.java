package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStackStyle;

public final class VerticalStackStyle //NOSONAR: A VerticalStackStyle is a LinearContainerStyle.
extends LinearContainerStyle<IVerticalStackStyle>
implements IVerticalStackStyle {

  public VerticalStackStyle() {
    initialize();
  }
}
