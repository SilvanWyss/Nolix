//package declaration
package ch.nolix.template.webgui.style;

//own imports
import ch.nolix.systemapi.elementapi.styleapi.IStyle;

//class
public final class StyleCatalogue {

  //constant
  public static final IStyle DARK_STYLE = new DarkStyleCreator().createDarkStyle();

  //constructor
  private StyleCatalogue() {
  }
}
