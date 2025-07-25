package ch.nolix.system.webgui.layertool;

import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

public final class LayerTool {

  public HtmlAttribute createIdHtmlAttributeForLayer(final ILayer<?> layer) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, layer.getInternalId());
  }
}
