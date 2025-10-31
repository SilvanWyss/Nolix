package ch.nolix.system.webcontainercontrol.horizontalstack;

import ch.nolix.system.webgui.linearcontainer.AbstractLinearContainerStyle;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStackStyle;

public final class HorizontalStackStyle //NOSONAR: A HorizontalStackStyle is a LinearContainerStyle.
extends AbstractLinearContainerStyle<IHorizontalStackStyle>
implements IHorizontalStackStyle {
  public HorizontalStackStyle() {
    initialize();
  }
}
