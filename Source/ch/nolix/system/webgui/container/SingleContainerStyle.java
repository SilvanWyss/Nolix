package ch.nolix.system.webgui.container;

import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainerStyle;

public final class SingleContainerStyle
extends ControlStyle<ISingleContainerStyle>
implements ISingleContainerStyle {

  public SingleContainerStyle() {
    initialize();
  }
}
