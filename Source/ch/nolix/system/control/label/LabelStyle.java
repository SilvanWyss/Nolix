/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.label;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.control.label.ILabelStyle;

/**
 * @author Silvan Wyss
 */
public final class LabelStyle extends AbstractControlStyle<ILabelStyle> implements ILabelStyle {
  public LabelStyle() {
    initialize();
  }
}
