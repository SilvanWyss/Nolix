package ch.nolix.system.webgui.atomiccontrol;

import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class ButtonHtmlBuilder implements IControlHtmlBuilder<IButton> {

  @Override
  public IHtmlElement createHtmlElementForControl(final IButton button) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalogue.BUTTON,
      button.getText());
  }
}
