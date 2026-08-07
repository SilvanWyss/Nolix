/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.button;

import ch.nolix.baseapi.document.node.Node;
import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.control.button.IButtonStyle;

/**
 * @author Silvan Wyss
 */
public final class ButtonStyle extends AbstractControlStyle<IButtonStyle> implements IButtonStyle {
  public ButtonStyle() {
    initialize();
  }

  public static ButtonStyle fromSpecification(final Node<?> specification) {
    final var buttonStyle = new ButtonStyle();
    buttonStyle.resetFromSpecification(specification);

    return buttonStyle;
  }
}
