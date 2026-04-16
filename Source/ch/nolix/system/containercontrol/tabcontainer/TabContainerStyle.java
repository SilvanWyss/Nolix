/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.tabcontainer;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.containercontrol.tabcontainer.ITabContainerStyle;

/**
 * @author Silvan Wyss
 */
public final class TabContainerStyle extends AbstractControlStyle<ITabContainerStyle> implements ITabContainerStyle {
  /**
   * Creates a new {@link TabContainerStyle}.
   */
  public TabContainerStyle() {
    initialize();
  }
}
