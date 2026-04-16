/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.verticalstack;

import ch.nolix.system.containercontrol.linearcontainer.AbstractLinearContainerStyle;
import ch.nolix.systemapi.containercontrol.verticalstack.IVerticalStackStyle;

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
