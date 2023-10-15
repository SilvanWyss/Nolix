//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILinkStyle;

//class
public final class LinkStyle extends ControlStyle<ILinkStyle> implements ILinkStyle {

  // constructor
  public LinkStyle() {
    initialize();
  }
}
