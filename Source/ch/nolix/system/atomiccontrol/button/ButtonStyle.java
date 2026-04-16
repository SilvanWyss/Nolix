/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.button;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.atomiccontrol.button.IButtonStyle;

/**
 * @author Silvan Wyss
 */
public final class ButtonStyle extends AbstractControlStyle<IButtonStyle> implements IButtonStyle {
  public ButtonStyle() {
    initialize();
  }
}
