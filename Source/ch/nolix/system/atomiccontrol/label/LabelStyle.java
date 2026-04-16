/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.label;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.atomiccontrol.label.ILabelStyle;

/**
 * @author Silvan Wyss
 */
public final class LabelStyle extends AbstractControlStyle<ILabelStyle> implements ILabelStyle {
  public LabelStyle() {
    initialize();
  }
}
