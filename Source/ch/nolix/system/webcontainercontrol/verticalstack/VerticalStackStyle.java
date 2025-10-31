package ch.nolix.system.webcontainercontrol.verticalstack;

import ch.nolix.system.webgui.linearcontainer.AbstractLinearContainerStyle;
import ch.nolix.systemapi.webcontainercontrol.verticalstack.IVerticalStackStyle;

public final class VerticalStackStyle //NOSONAR: A VerticalStackStyle is a LinearContainerStyle.
extends AbstractLinearContainerStyle<IVerticalStackStyle>
implements IVerticalStackStyle {
  public VerticalStackStyle() {
    initialize();
  }
}
