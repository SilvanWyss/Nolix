package ch.nolix.system.webgui.container;

import ch.nolix.system.webgui.controlstyle.AbstractControlStyle;
import ch.nolix.systemapi.webgui.container.ISingleContainerStyle;

public final class SingleContainerStyle
extends AbstractControlStyle<ISingleContainerStyle>
implements ISingleContainerStyle {

  public SingleContainerStyle() {
    initialize();
  }
}
