package ch.nolix.system.webgui.layertool;

import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

public final class LayerTool {

  public HtmlAttribute createIdHtmlAttributeForLayer(final ILayer<?> layer) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, layer.getInternalId());
  }
}
