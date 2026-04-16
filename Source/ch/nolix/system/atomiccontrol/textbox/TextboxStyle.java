/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.textbox;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.atomiccontrol.textbox.ITextboxStyle;

/**
 * @author Silvan Wyss
 */
public final class TextboxStyle extends AbstractControlStyle<ITextboxStyle> implements ITextboxStyle {
  public TextboxStyle() {
    initialize();
  }
}
