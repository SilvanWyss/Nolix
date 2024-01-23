//package declaration
package ch.nolix.system.webgui.controltool;

//own imports
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class ControlTool {

  //method
  public HtmlAttribute createIdHtmlAttributeForControl(final IControl<?, ?> control) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, control.getInternalId());
  }
}
