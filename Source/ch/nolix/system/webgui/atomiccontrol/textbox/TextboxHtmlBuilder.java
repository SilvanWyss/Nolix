package ch.nolix.system.webgui.atomiccontrol.textbox;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class TextboxHtmlBuilder implements IControlHtmlBuilder<ITextbox> {

  @Override
  public HtmlElement createHtmlElementForControl(final ITextbox textbox) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalog.INPUT,
      ImmutableList.withElement(
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.VALUE, textbox.getText())));
  }
}
