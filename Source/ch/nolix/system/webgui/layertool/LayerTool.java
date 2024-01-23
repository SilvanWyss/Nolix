//package declaration
package ch.nolix.system.webgui.layertool;

//own imports
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
public final class LayerTool {

  //method
  public HtmlAttribute createIdHtmlAttributeForLayer(final ILayer<?> layer) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, layer.getInternalId());
  }
}
