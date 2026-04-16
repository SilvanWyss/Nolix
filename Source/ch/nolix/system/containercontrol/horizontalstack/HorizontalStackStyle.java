/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.horizontalstack;

import ch.nolix.system.containercontrol.linearcontainer.AbstractLinearContainerStyle;
import ch.nolix.systemapi.containercontrol.horizontalstack.IHorizontalStackStyle;

/**
 * @author Silvan Wyss
 */
public final class HorizontalStackStyle //NOSONAR: A HorizontalStackStyle is a LinearContainerStyle.
extends AbstractLinearContainerStyle<IHorizontalStackStyle>
implements IHorizontalStackStyle {
  public HorizontalStackStyle() {
    initialize();
  }
}
