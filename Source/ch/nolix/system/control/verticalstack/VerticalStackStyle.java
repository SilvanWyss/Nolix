/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.verticalstack;

import ch.nolix.system.control.linearcontainer.AbstractLinearContainerStyle;
import ch.nolix.systemapi.control.verticalstack.IVerticalStackStyle;

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
