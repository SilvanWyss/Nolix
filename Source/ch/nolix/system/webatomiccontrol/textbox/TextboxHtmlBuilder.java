package ch.nolix.system.webatomiccontrol.textbox;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webatomiccontrol.textbox.ITextbox;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

public final class TextboxHtmlBuilder implements IControlHtmlBuilder<ITextbox> {
  @Override
  public HtmlElement createHtmlElementForControl(final ITextbox textbox) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalog.INPUT,
      ImmutableList.withElements(
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.VALUE, textbox.getText())));
  }
}
