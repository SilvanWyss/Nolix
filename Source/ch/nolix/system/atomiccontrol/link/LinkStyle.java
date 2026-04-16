/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.link;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.atomiccontrol.link.ILinkStyle;

/**
 * @author Silvan Wyss
 */
public final class LinkStyle extends AbstractControlStyle<ILinkStyle> implements ILinkStyle {
  public LinkStyle() {
    initialize();
  }
}
