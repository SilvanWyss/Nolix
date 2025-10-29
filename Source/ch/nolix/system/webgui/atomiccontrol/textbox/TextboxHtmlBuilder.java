package ch.nolix.system.webgui.atomiccontrol.textbox;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webgui.atomiccontrol.textbox.ITextbox;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

public final class TextboxHtmlBuilder implements IControlHtmlBuilder<ITextbox> {
  @Override
  public HtmlElement createHtmlElementForControl(final ITextbox textbox) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalog.INPUT,
      ImmutableList.withElement(
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.VALUE, textbox.getText())));
  }
}
