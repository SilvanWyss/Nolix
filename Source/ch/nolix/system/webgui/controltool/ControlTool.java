package ch.nolix.system.webgui.controltool;

import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalogue;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public final class ControlTool {

  public HtmlAttribute createIdHtmlAttributeForControl(final IControl<?, ?> control) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, control.getInternalId());
  }
}
