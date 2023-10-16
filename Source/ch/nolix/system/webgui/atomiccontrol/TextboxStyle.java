//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextboxStyle;

//class
public final class TextboxStyle extends ControlStyle<ITextboxStyle> implements ITextboxStyle {

  //constructor
  public TextboxStyle() {
    initialize();
  }
}
