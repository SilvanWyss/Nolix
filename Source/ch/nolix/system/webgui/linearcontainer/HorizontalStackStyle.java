package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStackStyle;

public final class HorizontalStackStyle //NOSONAR: A HorizontalStackStyle is a LinearContainerStyle.
extends LinearContainerStyle<IHorizontalStackStyle>
implements IHorizontalStackStyle {

  public HorizontalStackStyle() {
    initialize();
  }
}
