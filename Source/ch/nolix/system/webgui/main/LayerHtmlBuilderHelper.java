package ch.nolix.system.webgui.main;

import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.systemapi.webgui.main.ILayer;

public final class LayerHtmlBuilderHelper {
  private LayerHtmlBuilderHelper() {
  }

  public static IHtmlAttribute createIdHtmlAttributeForLayer(final ILayer<?> layer) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, layer.getInternalId());
  }
}
