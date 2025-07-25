package ch.nolix.system.webgui.linearcontainer;

import ch.nolix.systemapi.webgui.linearcontainer.IVerticalStackStyle;

public final class VerticalStackStyle //NOSONAR: A VerticalStackStyle is a LinearContainerStyle.
extends AbstractLinearContainerStyle<IVerticalStackStyle>
implements IVerticalStackStyle {

  public VerticalStackStyle() {
    initialize();
  }
}
