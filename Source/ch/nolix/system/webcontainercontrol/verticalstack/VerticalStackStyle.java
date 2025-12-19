package ch.nolix.system.webcontainercontrol.verticalstack;

import ch.nolix.system.webcontainercontrol.linearcontainer.AbstractLinearContainerStyle;
import ch.nolix.systemapi.webcontainercontrol.verticalstack.IVerticalStackStyle;

/**
 * @author Silvan Wyss
 */
public final class VerticalStackStyle //NOSONAR: A VerticalStackStyle is a LinearContainerStyle.
extends AbstractLinearContainerStyle<IVerticalStackStyle>
implements IVerticalStackStyle {
  public VerticalStackStyle() {
    initialize();
  }
}
