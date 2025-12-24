package ch.nolix.system.webatomiccontrol.link;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webatomiccontrol.link.ILink;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class LinkHtmlBuilder implements IControlHtmlBuilder<ILink> {
  @Override
  public IHtmlElement createHtmlElementForControl(final ILink control) {
    final var type = HtmlElementTypeCatalog.A;
    final var attributes = LinkHtmlBuilderHelper.createHtmlAttributesForControl(control);
    final var innerText = control.getDisplayText();

    return HtmlElement.withTypeAndAttributesAndInnerText(type, attributes, innerText);
  }
}
