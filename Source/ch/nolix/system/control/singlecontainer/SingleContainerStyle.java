/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.singlecontainer;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.control.singlecontainer.ISingleContainerStyle;

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
