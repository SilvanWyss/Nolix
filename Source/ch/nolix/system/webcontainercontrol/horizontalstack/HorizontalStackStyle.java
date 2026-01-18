/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.horizontalstack;

import ch.nolix.system.webcontainercontrol.linearcontainer.AbstractLinearContainerStyle;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStackStyle;

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
