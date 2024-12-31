package ch.nolix.system.webgui.atomiccontrol.textbox;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class TextboxHtmlBuilder implements IControlHtmlBuilder<ITextbox> {

  @Override
  public HtmlElement createHtmlElementForControl(final ITextbox textbox) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalogue.INPUT,
      ImmutableList.withElement(
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.VALUE, textbox.getText())));
  }
}
