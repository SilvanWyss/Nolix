/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.singlecontainer;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.containercontrol.singlecontainer.ISingleContainerStyle;

/**
 * @author Silvan Wyss
 */
public final class SingleContainerStyle
extends AbstractControlStyle<ISingleContainerStyle>
implements ISingleContainerStyle {
  public SingleContainerStyle() {
    initialize();
  }
}
