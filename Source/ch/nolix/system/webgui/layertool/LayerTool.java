package ch.nolix.system.webgui.layertool;

import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.systemapi.webgui.main.ILayer;

public final class LayerTool {
  public HtmlAttribute createIdHtmlAttributeForLayer(final ILayer<?> layer) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, layer.getInternalId());
  }
}
